package Controllers;

import Repositories.WalletRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;

public class WalletContoller {
    private static DataOutputStream toClient;
    private static WalletRepo walletRepo;
    private ObjectMapper mapper;
    
}
