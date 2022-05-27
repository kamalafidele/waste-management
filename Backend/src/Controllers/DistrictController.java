package Controllers;
import Config.DatabaseConnection;
import Models.District;
import Repositories.DistrictRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictController {

    private DataOutputStream toClient;
    private final ObjectMapper mapper;
    private final DistrictRepo districtRepo;
    private DatabaseConnection connection;

    public DistrictController(DatabaseConnection connection) {
        this.connection = connection;
        this.mapper = new ObjectMapper();
        this.districtRepo = new DistrictRepo(connection);
    };

    public void handleRequest(String request, DataOutputStream toClient){
        this.toClient = toClient;

        switch (request.split("/")[1]){
            case "getDistricts":
                getDistricts();
                break;
            default:
                sendResponse("request not specified");
                break;
        }
    }

    public void getDistricts() {
        List<District> districts = new ArrayList<>();
        ResultSet resultSet = districtRepo.findAll();
        try{

            while(resultSet.next()){
                District district= new District(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getLong(4));
                districts.add(district);
            }

            sendResponse(mapper.writeValueAsString(districts));
            System.out.println(districts);

        }catch( IOException | SQLException exception ){}
    }

    public void sendResponse(String response){
        try {
            toClient.writeUTF(response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
