package Controllers;

import Models.Company;
import Repositories.CompanyRepo;
import Repositories.WalletsRepoHandler;
import org.codehaus.jackson.map.ObjectMapper;
import Repositories.customerInvoicesRepo;

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
    customerInvoicesRepo customerInvoice;
    WalletsRepoHandler walletRepo;

    public CompanyController(){
        companyRepo=new CompanyRepo();
        analyticsController=new AnalyticsController();
        customerInvoice = new customerInvoicesRepo();
        walletRepo = new WalletsRepoHandler();
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
            case "addCompany":
                addCompany(request.split("/")[2]);
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

    public void addCompany(String data) {
        try{
            Company company=mapper.readValue(data,Company.class);

            if(companyRepo.save(company))
              sendResponse("Company added successfully");
            else
              sendResponse("Adding company failed! Try again");
        }catch (Exception exception){
            sendResponse("Adding company failed! Try again");
        }
    }

    public void getCompany(long companyId){
       ResultSet resultSet=companyRepo.findById(companyId);
       Company company=new Company();

       try{
           while(resultSet.next()){
               company.setId(resultSet.getInt(1));
               company.setName(resultSet.getString(2));
               company.setEmail(resultSet.getString(3));
               company.setPhone(resultSet.getString(4));
               company.setPin(resultSet.getLong(5));
               company.setRole(resultSet.getInt(6));
               company.setWalletId(resultSet.getInt(7));
               company.setLocation(resultSet.getInt(8));
           }

           sendResponse(mapper.writeValueAsString(company));

       } catch (IOException | SQLException exception){}
    }

    public void getCompanies() {
        List<Company> companies= new ArrayList<>();
        ResultSet resultSet=companyRepo.findAll();
        try{
            // THIS LOOP IS FOR INSERTING FETCHED COMPANIES TO THE LIST
            while(resultSet.next()){
                Company company= new Company(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                ,resultSet.getString(4),resultSet.getLong(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8));
                companies.add(company);
            }

            sendResponse(mapper.writeValueAsString(companies));

        }catch( IOException | SQLException exception ){}
    }

    public void createContract (String request){
        System.out.println(request.split("-")[0]+" "+request.split("-")[1]);
        int districtId = Integer.parseInt(request.split("-")[0]);
        int companyId = Integer.parseInt(request.split("-")[1]);
        if(companyRepo.createContract(districtId,companyId))
          sendResponse("Contract created successfully");
        else
          sendResponse("Creating contract failed! Try again");
    }

    // THIS A METHOD FOR SENDING RESPONSE TO THE CLIENT
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
