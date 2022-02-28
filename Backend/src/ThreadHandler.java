import Controllers.CompanyController;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS
    private CompanyController companyController;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
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
                case "company":
                    companyController.filterRequest(request,toClient);
                  break;
                case "wallet":
                    // a call to wallet controller
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
