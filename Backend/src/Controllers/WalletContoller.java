package Controllers;

import Repositories.WalletRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;

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
        int walletId = Integer.parseInt(request);
        
    }
}
