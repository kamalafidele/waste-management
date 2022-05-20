package Controllers;

import Models.Company;
import Models.Shifts;
import Repositories.ShiftsRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShiftsController {
    private DataOutputStream toClient;
    private ShiftsRepo shiftsRepo;
    private ObjectMapper mapper;

    public ShiftsController(){
        shiftsRepo = new ShiftsRepo();
        mapper=new ObjectMapper();
    }
    public void filterRequest(String request,DataOutputStream toClient) throws Exception {
        this.toClient = toClient;
        switch (request.split("/")[1]) {
            case "addShift":
                addShift(request.split("/")[2]);
            case "getShifts":
                getShifts();
            case "getShift":
                getShift(Integer.valueOf(request.split("/")[2]));
            default:
                sendResponse("Specify your request");
        }
    }
    public void addShift(String data){
        try{
           Shifts shift=mapper.readValue(data,Shifts.class);

            if(shiftsRepo.save(shift))
                sendResponse("shift added successfully");
            else
                sendResponse("Adding shift failed! Try again");
        }catch (Exception exception){
            sendResponse("Adding shift failed! Try again");
        }
    }
    public void getShift(int shiftId){
        ResultSet resultSet = shiftsRepo.findById(shiftId);
        Shifts shift = new Shifts();
        try{
            while(resultSet.next()){
                shift.setId(resultSet.getInt(1));
                shift.setCompany_id(resultSet.getInt(2));
                shift.setDate(resultSet.getString(3));
                shift.setConfirmerId(resultSet.getInt(4));
            }
            sendResponse(mapper.writeValueAsString(shift));
        }catch (IOException | SQLException e){
            e.getMessage();
        }
    }
    public void getShifts(){
        List <Shifts> shiftsList = new ArrayList<>();
        ResultSet resultSet = shiftsRepo.findAll();
        try{
            while (resultSet.next()){
                Shifts shift = new Shifts(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4));
                shiftsList.add(shift);
            }
            sendResponse(mapper.writeValueAsString(shiftsList));
        }catch (IOException | SQLException e){
            e.getMessage();
        }
    }

    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
