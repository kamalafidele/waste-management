package Controllers;

import Models.Shifts;
import Repositories.ServiceConfirmationRepo;
import Repositories.ShiftsRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            case "ConfirmService":
                addConfirmedService(request.split("/")[2]);
            case "getConfirmedServices":
                getConfirmedServices();
            case "getConfirmedService":
                getConfirmedService(Integer.valueOf(request.split("/")[2]));
            default:
                sendResponse("Specify your request");
        }

        public void getConfirmedService(int confirmedId){
            ResultSet resultSet = serviceConfirmationRepo.findById(confirmedId);
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
    }
}
