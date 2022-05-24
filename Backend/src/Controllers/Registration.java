package Controllers;

import Config.DatabaseConnection;
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
    private DatabaseConnection connection;
    private CompanyRepo companyRepo;
    private DistrictRepo districtRepo;
    private ObjectMapper mapper = new ObjectMapper();
    private WalletsRepoHandler walletRepo;
    private UserRepo userRepo;

    public Registration(DatabaseConnection connection) {
        this.connection = connection;
        this.districtRepo = new DistrictRepo(connection);
        this.companyRepo = new CompanyRepo(connection);
        this.walletRepo = new WalletsRepoHandler(connection);
        this.userRepo = new UserRepo(connection);
    }

    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient = toClient;

        switch (request.split("/")[1]) {
            case "register_company":
                registerCompany(request.split("/")[2], Long.valueOf(request.split("/")[3]));
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

    public void registerCompany(String data, Long districtId) {
        try {

            Company company = mapper.readValue(data, Company.class);
            Long walletId = handleWalletIssues();
            company.setWalletId(walletId);

            if(companyRepo.save(company)) {
                Long companiesCount = handleCompanyIssues();
                companyRepo.createContract(districtId,companiesCount);
                sendResponse("Company registered successfully");
            }

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
            while(resultSet.next()){
                walletCount = resultSet.getLong("totalWallets");
            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }

        return walletCount;
    }

    public Long handleCompanyIssues(){
        Long companyCount = 0L;
        try {
            ResultSet resultSet = companyRepo.findCompaniesCount();
            while(resultSet.next()){
                companyCount = resultSet.getLong("totalWallets");
            }
        } catch (Exception exception) {}

        return companyCount;
    }
}
