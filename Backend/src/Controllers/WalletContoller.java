package Controllers;

import Models.Wallet;
import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletContoller {
    private DataOutputStream toClient;
    private static WalletsRepoHandler walletRepo;
    private ObjectMapper mapper;
    private Wallet wallet;

    public void whichWallet( String request, DataOutputStream toClient ) {
        wallet = new Wallet();
        try {
            this.toClient = toClient;
            String ownerId = request.split("/")[2];
            switch (request.split("/")[1]) {
                case "admin":
                    getAdminWallet(ownerId);
                    break;
                case "company":
                    getCompanyWallet(ownerId);
                    break;
                case "district":
                    getDistrictWallet(ownerId);
                    break;
                case "client":
                    getUserWallet(ownerId);
                    break;
                default:
                    returnWallet("Please specify the wallet owner type!");
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            returnWallet("Please specify the wallet owner id!");
        }
    }

    public void returnWallet( String response ) {
        try {
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

    public WalletContoller(){
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
        
    }

    public void getDistrictWallet(String request){
        this.toClient = toClient;
        int districtId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByDistrictId(districtId);
        try{
            while(walletResult.next()){
                wallet.setBalance(walletResult.getInt(1));
            }
            returnWallet(String.valueOf(wallet.getBalance()));
        } catch (SQLException exception){}
    }

    public void getCompanyWallet(String request){
        this.toClient = toClient;
        int companyId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByCompanyId(companyId);
        try{
            while(walletResult.next()){
                wallet.setBalance(walletResult.getInt(1));
            }
            returnWallet(String.valueOf(wallet.getBalance()));
        } catch (SQLException exception){}
    }
}
