package Components;

import DataHandlers.WalletHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Wallet {
    DataOutputStream outToServer;
    DataInputStream fromServer;
    String userRole="company";
    Integer userId=1;

    public Wallet(DataOutputStream toServer, DataInputStream fromServer) {
        this.outToServer=toServer;
        this.fromServer=fromServer;
    }


    public void showWallet(){
        String request="wallet/"+userRole+"/"+userId;
        try {
            outToServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.printf("Your wallet balance is: " + response +" Rwf");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void checkTheUserRole() {
        //This will be a method to check the role of the user that's logged in
    }
}
