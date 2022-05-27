package Controllers;

import Config.DatabaseConnection;
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
    private DatabaseConnection connection;

    public ShiftsController(DatabaseConnection connection){
        this.connection = connection;
        shiftsRepo = new ShiftsRepo(connection);
        mapper=new ObjectMapper();
    }
    public void filterRequest(String request,DataOutputStream toClient) throws Exception {
        this.toClient = toClient;
        switch (request.split("/")[1]) {
            case "addShift":
                addShift(request.split("/")[2]);
                break;
            case "initShift":
                initShift();
                break;
            case "closeShift":
                closeShift();
                break;
            case "getShifts":
                getShifts();
                break;
            case "getShift":
                getShift(Integer.valueOf(request.split("/")[2]));
                break;
            default:
                sendResponse("Specify your request");
                break;
        }
    }
    public void initShift(){
        try{
            if(shiftsRepo.initializeShift()) {
                System.out.println("Shift on");
                sendResponse("shift on");
            }
            else
                sendResponse("Initializing shift failed! Try again");
        }catch (Exception exception){
            sendResponse("Initializing shift failed! Try again");
        }
    }
    public void closeShift(){
        try{
            if(shiftsRepo.closeShift()) {
                System.out.println("Shift off");
                sendResponse("shift off");
            }
            else
                sendResponse("closing shift failed! Try again");
        }catch (Exception exception){
            sendResponse("closing shift failed! Try again");
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
    private int getRowCount(ResultSet rs) {

        try {

            if(rs != null) {

                rs.last();

                return rs.getRow();
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    private int getColumnCount(ResultSet rs) {

        try {

            if(rs != null)
                return rs.getMetaData().getColumnCount();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    public void getShifts() throws SQLException {
        List <Shifts> shiftsList = new ArrayList<>();
        ResultSet resultSet = shiftsRepo.findAll();
        try{
            while (resultSet.next()){
                Shifts shift = new Shifts(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5));
                shiftsList.add(shift);
            }
            int rowCount = getRowCount(resultSet);
            int columnCount = getColumnCount(resultSet);

            Object data[][] = new Object[rowCount][columnCount];

            resultSet.beforeFirst();

            int i = 0;
            while(resultSet.next()){
                int j = 0;
                data[i][j++] = resultSet.getString(5);
                data[i][j++] = resultSet.getString(3);
//                data[i][j++] = resultSet.getInt(1);
                data[i][j++] = resultSet.getInt(2);
                data[i][j++] = "Waste collection";
//                data[i][j++] = resultSet.getInt(4);

                i++;
            }

            sendResponse(mapper.writeValueAsString(data));
//            sendResponse(mapper.writeValueAsString(shiftsList));
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
