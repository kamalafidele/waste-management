import Config.DatabaseConnection;
import Controllers.*;
import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;
    DatabaseConnection databaseConnection;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        this.databaseConnection = new DatabaseConnection();
    }


    @Override
    public void run(){
        while (true){
            try{

                System.out.println("Client connected");

                DataInputStream fromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

                //READING REQUESTS FROM THE CLIENT
                String request=fromClient.readUTF();
                switch (request.split("/")[0]){
                    case "serviceconfirmation":
                        new ShiftsController(databaseConnection).filterRequest(request,toClient);
                    case "company":
                        new CompanyController(databaseConnection).filterRequest(request,toClient);
                        break;
                    case "district":
                        new DistrictController(databaseConnection).handleRequest(request,toClient);
                        break;
                    case "payment":
                        new PaymentController(databaseConnection).filterRequest(request,toClient);
                        break;
                    case "wallet":
                        new WalletController(databaseConnection).whichWallet(request,toClient);
                        break;
                    case "notification":
                        new NotificationController(databaseConnection).filterRequest(request,toClient);
                    case "debt":
                        new DebtController(databaseConnection).filterRequest(request,toClient);
                    case "registration":
                        new Registration(databaseConnection).filterRequest(request,toClient);
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
