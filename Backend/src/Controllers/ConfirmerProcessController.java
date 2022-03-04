package Controllers;
import Models.ConfirmerProcess;
import Repositories.ConfirmerProcessRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.*;
public class ConfirmerProcessController {
    private DataOutputStream toClient;
    private ConfirmerProcessRepo confirmerProcessRepo;
    private ObjectMapper mapper;

    public ConfirmerProcessController(){
        confirmerProcessRepo = new ConfirmerProcessRepo();
        mapper = new ObjectMapper();
    }
    public void RequestType(String request,DataOutputStream toClient) {
        this.toClient = toClient;
        switch (request.split("/")[1]) {
            case "getALl":
                getAllConfirmed();
                break;
            case "getOneConfirmed":
                getConfirmed(request.split("/")[2]);
                break;

            case "insert":
                addConfirmedHouse(request.split("/")[2]);
                break;

            default:
                SendResponse("Your request is not valid! Try again");
                break;
        }
    }

    public void SendResponse(String str){
        try{
            toClient.writeUTF(str);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addConfirmedHouse(String input){
        try{
            ConfirmerProcess confirmerProcess = mapper.readValue(input,ConfirmerProcess.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getConfirmed(String housecode){
        ResultSet resultSet = confirmerProcessRepo.findByHouseCode(housecode);
        ConfirmerProcess confirmerProcess = new ConfirmerProcess();
        try {
            while(resultSet.next()){
                confirmerProcess.setId((resultSet.getInt(1)));
                confirmerProcess.setVillage(resultSet.getString(2));
                confirmerProcess.setCompany(resultSet.getString(3));
                confirmerProcess.setHouseCode(resultSet.getString(4));
            }
            SendResponse(mapper.writeValueAsString(confirmerProcess));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getAllConfirmed(){
        List<ConfirmerProcess> confirmedlist = new ArrayList<>();
        ResultSet resultsAll = confirmerProcessRepo.findAll();
        try{
            while (resultsAll.next()){
                ConfirmerProcess confirmed = new ConfirmerProcess(resultsAll.getInt(1), resultsAll.getString(2), resultsAll.getString(3),resultsAll.getString(4) );
                confirmedlist.add(confirmed);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

