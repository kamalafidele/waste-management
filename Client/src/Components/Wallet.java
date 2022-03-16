package Components;

import DataHandlers.HouseHandler;
import DataHandlers.WalletHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Wallet {
    DataOutputStream outToServer;
    DataInputStream fromServer;

    public Wallet(DataOutputStream toServer, DataInputStream fromServer) {
        this.outToServer=toServer;
        this.fromServer=fromServer;
    }


    public void showWallet(int userId){
        String request="wallet/" + userId;
        try {
            outToServer.writeUTF(request);
            System.out.println("SENT REQUEST");
            String response=fromServer.readUTF();
            System.out.printf("Your wallet balance is: " + response +" Rwf");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
