package Controllers;

import Config.DatabaseConnection;
import Models.Company;
import Repositories.CompanyRepo;
import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;
import Repositories.CustomerInvoicesRepo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyController {
    private DataOutputStream toClient;
    private CompanyRepo companyRepo;
    private ObjectMapper mapper;
    AnalyticsController analyticsController;
    CustomerInvoicesRepo customerInvoice;
    private DatabaseConnection connection;

    public CompanyController(DatabaseConnection connection){
        this.connection = connection;
        companyRepo=new CompanyRepo(connection);
        analyticsController=new AnalyticsController(connection);
        customerInvoice = new CustomerInvoicesRepo(connection);
        mapper=new ObjectMapper();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient=toClient;

        switch (request.split("/")[1]) {
            case "getAll":
                getCompanies();
              break;
            case "getSingle":
                getCompany(Long.valueOf(request.split("/")[2]));
              break;
            case "createContract":
                createContract(request.split("/")[2]);
            case "getInvoices":
                customerInvoice.getInvoices(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            case "downloadInvoice":
                customerInvoice.downloadInvoice(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            case "analytics":
                analyticsController.filterRequest(request, toClient);
                break;

            default:
                sendResponse("Please specify your request");
              break;
        }
    }

    public void getCompany(long companyId){
       ResultSet resultSet=companyRepo.findById(companyId);
       Company company= extractCompany(resultSet);
       try {
           sendResponse(mapper.writeValueAsString(company));
       } catch (Exception exception){}
    }

    public void getCompanies() {
        List<Company> companies= new ArrayList<>();
        ResultSet resultSet = companyRepo.findAll();
        try{

            while(resultSet.next()) {
                Company company= new Company(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),
                resultSet.getLong(4),resultSet.getLong(5));
                companies.add(company);
            }

            sendResponse(mapper.writeValueAsString(companies));

        }catch( IOException | SQLException exception ){}
    }

    public void createContract (String request) {
        System.out.println(request.split("-")[0]+" "+request.split("-")[1]);
        int districtId = Integer.parseInt(request.split("-")[0]);
        int companyId = Integer.parseInt(request.split("-")[1]);
        if(companyRepo.createContract(districtId,companyId))
          sendResponse("Contract created successfully");
        else
          sendResponse("Creating contract failed! Try again");
    }

    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

    public Company extractCompany(ResultSet resultSet){
        Company company = new Company();
        try{
            while(resultSet.next()){
                company.setId(resultSet.getLong(1));
                company.setName(resultSet.getString(2));
                company.setEmail(resultSet.getString(3));
                company.setTin(resultSet.getLong(4));
                company.setWalletId(resultSet.getLong(5));
            }
        } catch (Exception exception){}

        return company;
    }
}
