package Controllers;

import Config.DatabaseConnection;
import Models.Company;
import Repositories.CompanyRepo;
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

    public CompanyController(){
        companyRepo=new CompanyRepo();
        analyticsController=new AnalyticsController();
        mapper=new ObjectMapper();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient=toClient;
        // Get invoice controllers
        customerInvoicesRepo customerInvoice = new customerInvoicesRepo();
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
        }catch (IOException exception){
            sendResponse("Adding company failed! Try again");
        }
    }

    public void getCompany(long companyId){
       ResultSet resultSet=companyRepo.findById(companyId);
       Company company=new Company();

       try{
           while(resultSet.next()){
               company.setId(resultSet.getLong(1));
               company.setName(resultSet.getString(2));
               company.setEmail(resultSet.getString(3));
               company.setPaymentCode(resultSet.getLong(4));
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
                Company company=new Company(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3)
                ,resultSet.getLong(4));
                companies.add(company);
            }

            sendResponse(mapper.writeValueAsString(companies));

        }catch( IOException | SQLException exception ){}
    }

    // THIS A METHOD FOR SENDING
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
