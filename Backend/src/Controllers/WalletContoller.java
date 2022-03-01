package Controllers;

import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;

public class WalletContoller {
    private DataOutputStream toClient;
    private static WalletsRepoHandler walletRepo;
    private ObjectMapper mapper;

    public void whichWallet( String request, DataOutputStream toClient ) {
        try {
            this.toClient = toClient;
            String ownerId = request.split("/")[2];
            switch (request.split("/")[1]) {
                case "admin":
                    getAdminWallet(ownerId, toClient);
                    break;
                case "company":
                    getCompanyWallet(ownerId, toClient);
                    break;
                case "district":
                    getDistrictWallet(ownerId, toClient);
                    break;
                case "client":
                    getUserWallet(ownerId, toClient);
                    break;
                default:
                    returnWallet("Please specify the wallet owner type!");
                    break;
            }
        }
        catch (Exception e){
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

    public WalletContoller(){
        walletRepo = new WalletsRepoHandler();
        mapper = new ObjectMapper();
    }

    public void getUserWallet(String request, DataOutputStream toClient){
        this.toClient = toClient;
        int userId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByUserId(userId);
    }

    public void getAdminWallet(String request, DataOutputStream toClient){
        this.toClient = toClient;
        int adminId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByAdminId(adminId);
    }

    public void getDistrictWallet(String request, DataOutputStream toClient){
        this.toClient = toClient;
        int districtId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByDistrictId(districtId);
    }

    public void getCompanyWallet(String request, DataOutputStream toClient){
        this.toClient = toClient;
        int companyId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByCompanyId(companyId);
    }
}
