package Components;

import java.io.*;

public class Payment {
    DataOutputStream outToServer;
    DataInputStream fromServer;
    public void checkDebt(DataOutputStream outToServer,DataInputStream fromServer){
        this.outToServer=outToServer;
        this.fromServer=fromServer;
        String request="payment/checkDebt";
        try{
            outToServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
}
