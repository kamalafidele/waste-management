package Controllers;

import Models.Company;
import Repositories.CompanyRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompanyController {
    DataOutputStream toClient;
    private final CompanyRepo companyRepo;

    public CompanyController(){
        companyRepo=new CompanyRepo();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest( String request, DataOutputStream toClient ) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "getAll" -> getCompanies();
            case "insert" -> addCompany();
            default -> sendResponse("Please specify your request");
        }
    }

    //THIS METHOD ADDS A COMPANY TO THE DATABASE
    public void addCompany() {

    }


     //THIS METHOD GETS ALL COMPANIES IN THE DATABASE
    public void getCompanies() {
        List<Company> companies= new ArrayList<>();
        ResultSet resultSet=companyRepo.findAll();
        try{
            // THIS LOOP IS FOR INSERTING FETCHED COMPANIES TO THE LIST
            while(resultSet.next()){
                Company company=new Company(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3)
                ,resultSet.getLong(4),resultSet.getLong(5));
                companies.add(company);
            }
        }catch( SQLException exception ){
            exception.printStackTrace();
        }

        ObjectMapper mapper=new ObjectMapper();

        try{
            sendResponse(mapper.writeValueAsString(companies));
        }catch (IOException exception){
            exception.printStackTrace();
        }

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
