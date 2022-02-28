import Controllers.CompanyController;
import Controllers.PaymentController;

import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    //REGISTERING ALL CONTROLLERS
    private CompanyController companyController;
    private PaymentController paymentController;

    public ThreadHandler(Socket socket){
        this.socket=socket;
        companyController=new CompanyController();
        paymentController=new PaymentController();
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
                case "wallet":
                    // a call to wallet controller
                case "payment":
                    //payment controller
                    paymentController.checkRequest(request,toClient);
                default:
                    toClient.writeUTF("Undefined request");
                  break;
            }

            socket.close();
        }catch(IOException exception){}
    }

}
