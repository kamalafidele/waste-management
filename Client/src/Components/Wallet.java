package Components;

import DataHandlers.WalletHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Wallet {
    DataOutputStream outToServer;
    DataInputStream fromServer;

    WalletHandler balance;
    public void showWallet(DataOutputStream outToServer, DataInputStream fromServer){
        this.outToServer=outToServer;
        this.fromServer=fromServer;
        String request="wallet/getBalance";
        try {
            outToServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.printf("response: " + response);
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }
}
