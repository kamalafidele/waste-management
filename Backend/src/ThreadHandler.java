import Controllers.CompanyController;
import Controllers.HouseController;
import Controllers.PaymentController;
import Controllers.WalletContoller;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS
    private CompanyController companyController;
    private HouseController houseController;
    private PaymentController paymentController;
    private WalletContoller walletContoller;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
        houseController=new HouseController();
        paymentController=new PaymentController();
        walletContoller = new WalletContoller();
    }

    @Override
    public void run(){
        try{
            CompanyController company = new CompanyController();
            System.out.println("Client connected");

            DataInputStream fromClient=new DataInputStream(socket.getInputStream());
            DataOutputStream toClient=new DataOutputStream(socket.getOutputStream());

            //READING REQUESTS FROM THE CLIENT
            String request=fromClient.readUTF();
            
            switch (request.split("/")[0]){
                case "admin":
                    break;
                case "company":
                    companyController.filterRequest(request,toClient);
                  break;
                case "citizen":
                    houseController.filterRequest(request,toClient);
                    break;
                case "payment":
                    paymentController.filterRequest(request,toClient);
                    break;
                case "wallet":
                    /*
                    * wallets endpoint
                    * /wallet/(admin|company|district|user)/id
                    * */
                    walletContoller.whichWallet(request, toClient);
                    break;
                default:
                    System.out.println(request.split("/")[0]);
                    toClient.writeUTF("Undefined request");
                  break;
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
