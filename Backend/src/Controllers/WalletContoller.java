package Controllers;

import Repositories.WalletRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.sql.ResultSet;

public class WalletContoller {
    private DataOutputStream toClient;
    private static WalletRepo walletRepo;
    private ObjectMapper mapper;

    public WalletContoller(){
        walletRepo = new WalletRepo();
        mapper = new ObjectMapper();
    }

    public void getWallet(String request, DataOutputStream toClient){
        this.toClient = toClient;
        int userId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletById(userId);
    }
}
