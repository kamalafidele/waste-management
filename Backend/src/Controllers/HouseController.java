package Controllers;

import Models.House;
import Repositories.HouseRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseController {
    private DataOutputStream toClient;
    private HouseRepo houseRepo;
    private ObjectMapper mapper;

    public HouseController(){
        houseRepo=new HouseRepo();
        mapper=new ObjectMapper();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest(String request, DataOutputStream toClient) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
//            case "getAll":
//                getClients();
//                break;
            case "create":
                createClientTable();
                break;
            case "getSingle":
                getOneClient(String.valueOf(request.split("/")[2]));
                break;
            case "insert":
                addClient(request.split("/")[2]);
                break;
            default:
                sendResponse("Please specify your request");
                break;
        }
    }

    public void createClientTable() {
        try{
            houseRepo.createClientTable();

            sendResponse("ClientTable create successfully");
        }catch ( Exception e){}
    }

    public void addClient(String data) {
        try{
            House house=mapper.readValue(data,House.class);
            houseRepo.save(house);

            sendResponse("Client added successfully");
        }catch (IOException exception){}
    }

    //login&profile
    public void getOneClient(String token){
        ResultSet resultSet=houseRepo.findByToken(token);
        House house=new House();

        try{
            if (!resultSet.next()){
                System.out.println("No such user");
            }
//            while(resultSet.next()){
                house.setFullnames(resultSet.getString(2));
                house.setNid(resultSet.getString(3));
                house.setHouseno(resultSet.getString(4));
                house.setTelno(resultSet.getString(5));
                house.setSector(resultSet.getString(6));
                house.setCell(resultSet.getString(7));
                house.setVillage(resultSet.getString(8));
                house.setToken(resultSet.getString(9));
//            }

            sendResponse(mapper.writeValueAsString(house));
            System.out.println("House found " + mapper.writeValueAsString(house));

        } catch (IOException | SQLException exception){}
    }

//    public void getClients() {
//        List<House> houses= new ArrayList<>();
//        ResultSet resultSet=houseRepo.findAll();
//        try{
//            // THIS LOOP IS FOR INSERTING FETCHED COMPANIES TO THE LIST
//            while(resultSet.next()){
//                House house=new House(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
//                houses.add(house);
//            }
//
//            sendResponse(mapper.writeValueAsString(houses));
//
//        }catch( IOException | SQLException exception ){}
//    }

    // THIS A METHOD FOR SENDING
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

}
