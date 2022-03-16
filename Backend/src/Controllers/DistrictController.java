package Controllers;
import Models.District;
import Repositories.DistrictRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;

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
            case "login":
                login(request.split("/")[2]);
                break;
            case "analytics":
                new AnalyticsController().filterRequest(request, toClient);
                break;
            default:
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

    public void sendResponse(String response){
        try {
            toClient.writeUTF(response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
