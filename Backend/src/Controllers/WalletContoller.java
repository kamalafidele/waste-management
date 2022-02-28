package Controllers;

import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.sql.ResultSet;

public class WalletContoller {
    private DataOutputStream toClient;
    private static WalletsRepoHandler walletRepo;
    private ObjectMapper mapper;

    public WalletContoller(){
        walletRepo = new WalletsRepoHandler();
        mapper = new ObjectMapper();
    }

    public void getUserWallet(String request, DataOutputStream toClient){
        this.toClient = toClient;
        int userId = Integer.parseInt(request);
        ResultSet walletResult = walletRepo.findWalletByUserId(userId);
    }

    
}
