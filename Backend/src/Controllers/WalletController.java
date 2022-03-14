package Controllers;

import Models.Wallet;
import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletController {
    private DataOutputStream toClient;
    private static WalletsRepoHandler walletRepo;
    private ObjectMapper mapper;
    private Wallet wallet;

    public void whichWallet( String request, DataOutputStream toClient ) {
        wallet = new Wallet();
        System.out.println(request);
        try {
            this.toClient = toClient;
            String ownerId = request.split("/")[1];
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            returnWallet("Please specify the wallet owner id!");
        }
    }

    public void returnWallet( String response ) {
        try {
            System.out.println("SENT RESPONSE");
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

    public void getWalletBalance(ResultSet walletBalance) {
        try{
            while(walletBalance.next()){
                wallet.setBalance(walletBalance.getInt(1));
            }
            returnWallet(String.valueOf(wallet.getBalance()));
        } catch (SQLException exception){}
    }

    public WalletController(){
        walletRepo = new WalletsRepoHandler();
        mapper = new ObjectMapper();
    }

    public void getUserWallet(String request){
        this.toClient = toClient;
        int userId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByUserId(userId);
        getWalletBalance(walletResult);
    }

    public void getAdminWallet(String request){
        this.toClient = toClient;
        int adminId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByAdminId(adminId);
        getWalletBalance(walletResult);
    }

    public void getDistrictWallet(String request){
        this.toClient = toClient;
        int districtId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByDistrictId(districtId);
        getWalletBalance(walletResult);
    }
    
}
