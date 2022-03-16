package Controllers;
import Models.Company;
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

    public DistrictController(){
        this.mapper = new ObjectMapper();
        this.districtRepo = new DistrictRepo();
    };

    public void handleRequest(String request, DataOutputStream toClient){
        this.toClient = toClient;

        switch (request.split("/")[1]){
            case "getDistricts":
                getDistricts();
                break;
            case "addDistrict":

                addDistrict(request.split("/")[2]);
                break;
            case "login":

                login(request.split("/")[2]);
                break;
            default:
                sendResponse("request not specified");
                break;
        }
    }

    public void login(String data){
        try {
            District district = mapper.readValue(data, District.class);

            if(districtRepo.login(district)){
                sendResponse("true");
            }else{
                sendResponse("false");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addDistrict(String data) {
        try{
            District district=mapper.readValue(data,District.class);

            if(districtRepo.save(district))
                sendResponse("Successvelly  added");
            else
                sendResponse("Failed to add.Try again");
        }catch (Exception exception){
//            sendResponse("Failed to add.Try again");
            exception.printStackTrace();
        }
    }
    public void getDistricts() {
        List<District> districts= new ArrayList<>();
        ResultSet resultSet=districtRepo.findAll();
        try{
            // THIS LOOP IS FOR INSERTING FETCHED COMPANIES TO THE LIST
            while(resultSet.next()){
                District district= new District(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getString(4),resultSet.getLong(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8));
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
