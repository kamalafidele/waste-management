import Controllers.CompanyController;
import Controllers.NotificationController;

import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS
    private CompanyController companyController;
    private NotificationController notificationController;
    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
        notificationController = new NotificationController();
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

                    break;
                case "company":
                    companyController.filterRequest(request,toClient);
                  break;
                case "notification":
                    notificationController.filterRequest(request,toClient);
                case "wallet":
                    // a call to wallet controller
                default:
                    toClient.writeUTF("Undefined request");
                  break;
            }

            socket.close();
        }catch(IOException exception){}
    }

}
