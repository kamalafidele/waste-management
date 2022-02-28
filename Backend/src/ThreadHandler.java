import Controllers.CompanyController;
import Controllers.HouseController;

import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS
    private CompanyController companyController;
    private HouseController houseController;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
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
                case "citizen":
                    houseController.filterRequest(request,toClient);
                    break;
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
