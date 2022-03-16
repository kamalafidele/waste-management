package Components;

import DataHandlers.HouseHandler;
import DataHandlers.WalletHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Wallet {
    DataOutputStream outToServer;
    DataInputStream fromServer;

    Integer companyId=1;

    public Wallet(DataOutputStream toServer, DataInputStream fromServer) {
        this.outToServer=toServer;
        this.fromServer=fromServer;
    }


    public void showWallet(){
        String request="wallet/" + companyId;
        try {
            outToServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
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
