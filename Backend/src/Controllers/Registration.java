package Controllers;

import Models.Company;
import Models.District;
import Models.User;
import Repositories.CompanyRepo;
import Repositories.DistrictRepo;
import Repositories.UserRepo;
import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.sql.ResultSet;

public class Registration {
    private DataOutputStream toClient;
    private CompanyRepo companyRepo = new CompanyRepo();
    private DistrictRepo districtRepo = new DistrictRepo();
    private ObjectMapper mapper = new ObjectMapper();
    private WalletsRepoHandler walletRepo = new WalletsRepoHandler();
    private UserRepo userRepo = new UserRepo();

    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient = toClient;

        switch (request.split("/")[1]) {
            case "register_company":
                registerCompany(request.split("/")[2]);
                break;
            case "register_district":
                registerDistrict(request.split("/")[2]);
                break;
            case "register_user":
                registerUser(request.split("/")[2]);
                break;
            default:
                sendResponse("Please specify your request");
                break;
        }
    }

    public void registerCompany(String data){
        try {
            Company company = mapper.readValue(data, Company.class);
            company.setWalletId(handleWalletIssues());

            if(companyRepo.save(company))
                sendResponse("Company registered successfully");

            sendResponse("Registering company failed! Try again");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void registerDistrict(String data) {
         try {
             District district = mapper.readValue(data, District.class);
             district.setWalletId(handleWalletIssues());

             if(districtRepo.save(district))
                 sendResponse("District registered successfully");

             sendResponse("Registering District failed! Try again");
         } catch (Exception e) {}
    }

    public void registerUser(String data) {
        try {
            User user = mapper.readValue(data, User.class);
            user.setWallet(handleWalletIssues());
            if(userRepo.save(user))
                sendResponse("User added successfully");

            sendResponse("Adding user failed! Try again");
        } catch (Exception exception) {}
    }

    public void sendResponse(String response) {
        try {
            toClient.writeUTF(response);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Long handleWalletIssues() {
        Long walletCount = 0L;
        try {
            walletRepo.addWallet();
            ResultSet resultSet = walletRepo.findWalletsCount();
             walletCount = resultSet.getLong("totalWallets");
        }catch (Exception exception) {}

        return walletCount;
    }
}