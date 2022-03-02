import Controllers.AdminController;
import Controllers.CompanyController;
import Controllers.NotificationController;
import Controllers.HouseController;
import Controllers.PaymentController;
import Controllers.WalletContoller;

import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS

    private final CompanyController companyController;
    private final NotificationController notificationController;
    private final HouseController houseController;
    private final WalletContoller walletContoller;
    private final PaymentController paymentController;
    private final AdminController adminController;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
        notificationController = new NotificationController();
        houseController=new HouseController();
        paymentController=new PaymentController();
        walletContoller = new WalletContoller();
        adminController = new AdminController();
    }


    @Override
    public void run(){
        try{
            System.out.println("Client connected");

            DataInputStream fromClient=new DataInputStream(socket.getInputStream());
            DataOutputStream toClient=new DataOutputStream(socket.getOutputStream());

            //READING REQUESTS FROM THE CLIENT
            String request=fromClient.readUTF();
            switch (request.split("/")[0]){
                case "admin":
                    adminController.handleRequest(request, toClient);
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
                    // a call to wallet controller
                    /*
                    * wallets endpoint
                    * /wallet/(admin|company|district|user)/id
                    * */
                    walletContoller.whichWallet(request, toClient);
                    break;
                case "notification":
                    notificationController.filterRequest(request,toClient);
                default:
                    toClient.writeUTF("Undefined request");
                  break;
            }
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
