import Controllers.*;
import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS
    private final CompanyController companyController = new CompanyController();
    private final NotificationController notificationController = new NotificationController();
    private final WalletController walletController = new WalletController();
    private final PaymentController paymentController = new PaymentController();
    private final DebtController debtController = new DebtController();
    private final ShiftsController shiftsController = new ShiftsController();
    private final DistrictController districtController = new DistrictController();
    private final ServiceConfirmationController serviceConfirmationController = new ServiceConfirmationController();
    private final  Registration registration = new Registration();

    public ThreadHandler(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run(){
        while (true){
            try{

                System.out.println("Client connected");

                DataInputStream fromClient=new DataInputStream(socket.getInputStream());
                DataOutputStream toClient=new DataOutputStream(socket.getOutputStream());

                //READING REQUESTS FROM THE CLIENT
                String request=fromClient.readUTF();
                switch (request.split("/")[0]){
                    case "serviceconfirmation":
                        shiftsController.filterRequest(request,toClient);
                    case "company":
                        companyController.filterRequest(request,toClient);
                        break;
                    case "district":
                        districtController.handleRequest(request,toClient);
                        break;
                    case "payment":
                        paymentController.filterRequest(request,toClient);
                        break;
                    case "wallet":
                        walletController.whichWallet(request, toClient);
                        break;
                    case "notification":
                        notificationController.filterRequest(request,toClient);
                    case "debt":
                        debtController.filterRequest(request,toClient);
                    case "registration":
                        registration.filterRequest(request,toClient);
                        break;
                    default:
                        toClient.writeUTF("Undefined request");
                        break;
                }
            } catch (Exception e) {
                 break;
            }
        }

    }

}
