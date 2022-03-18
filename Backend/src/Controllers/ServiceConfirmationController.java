package Controllers;

import Models.Admin;
import Models.ServiceConfirmation;
import Models.Shifts;
import Repositories.AdminRepo;
import Repositories.ServiceConfirmationRepo;
import Repositories.ShiftsRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceConfirmationController {
    private DataOutputStream toClient;
    private ServiceConfirmationRepo serviceConfirmationRepo;
    private ObjectMapper mapper;

    public ServiceConfirmationController(){
        serviceConfirmationRepo = new ServiceConfirmationRepo();
        mapper= new ObjectMapper();
    }

    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
    public void filterRequest(String request,DataOutputStream toClient) throws Exception {
        this.toClient = toClient;
        switch (request.split("/")[1]) {
            case "login":
             login(request.split("/")[2]);
             break;
            case "addConfirmedService":
                addConfirmedService(request.split("/")[2]);
                break;
            case "getConfirmedServices":
                getConfirmedServices();
                break;
            case "getConfirmedService":
                getConfirmedService(Integer.valueOf(request.split("/")[2]));
                break;
            default:
                sendResponse("Specify your request__");
                break;
        }
    }
        public void getConfirmedService(int confirmedId){
            ResultSet resultSet = serviceConfirmationRepo.findById(confirmedId);
            ServiceConfirmation serviceConfirmation = new ServiceConfirmation();

            try{
                while(resultSet.next()){
                    serviceConfirmation.setId(resultSet.getInt(1));
                    serviceConfirmation.setServiceId(resultSet.getInt(2));
                    serviceConfirmation.setShiftId(resultSet.getInt(3));
                    serviceConfirmation.setConfirmerId(resultSet.getInt(4));
                }
                sendResponse(mapper.writeValueAsString(serviceConfirmation));
            }catch (IOException | SQLException e){
                e.getMessage();
            }
        }
    public void getConfirmedServices(){
        List<ServiceConfirmation> confirmedList = new ArrayList<>();
        ResultSet resultSet = serviceConfirmationRepo.findAll();
        try{
            while (resultSet.next()){
              ServiceConfirmation serviceConfirmation = new ServiceConfirmation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),resultSet.getInt(4));
               confirmedList.add(serviceConfirmation);
            }
            sendResponse(mapper.writeValueAsString(confirmedList));
        }catch (IOException | SQLException e){
            e.getMessage();
        }
    }
    public void login(String data){
        try{
            Admin confirmer = mapper.readValue(data,Admin.class);
            System.out.println("method login called");
             if(serviceConfirmationRepo.login(confirmer)) {
//
                 System.out.println("serviceConfirmationRepo seen");
                 sendResponse("true");
             }else {
                 sendResponse("false");
             }

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void addConfirmedService(String data){
        try{
            ServiceConfirmation serviceConfirmed = mapper.readValue(data,ServiceConfirmation.class);
            if(serviceConfirmationRepo.save(serviceConfirmed))
                sendResponse("Service confirmed successfully");
            else
                sendResponse("Service confirmation failed ! Try again");
        }catch (Exception e){
            e.getMessage();
        }
    }

}

