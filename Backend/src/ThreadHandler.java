import Controllers.*;
import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS

    private final CompanyController companyController;
    private final NotificationController notificationController;
    private final HouseController houseController;
    private final WalletController walletController;
    private final PaymentController paymentController;
    private final AdminController adminController;
    private final DebtController debtController;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
        notificationController = new NotificationController();
        houseController=new HouseController();
        paymentController=new PaymentController();
        walletController = new WalletController();
        adminController = new AdminController();
        debtController=new DebtController();
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
                    walletController.whichWallet(request, toClient);
                    break;
                case "notification":
                    notificationController.filterRequest(request,toClient);
                case "debt":
                    debtController.filterRequest(request,toClient);
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
