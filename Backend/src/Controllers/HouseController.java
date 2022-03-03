package Controllers;

import Models.House;
import Repositories.HouseRepo;
import Repositories.customerInvoicesRepo;
import org.codehaus.jackson.map.ObjectMapper;
import Repositories.customerInvoicesRepo;

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
    public void filterRequest(String request, DataOutputStream toClient) throws Exception {
        this.toClient=toClient;
        customerInvoicesRepo customerInvoice = new customerInvoicesRepo();
        switch (request.split("/")[1]) {
//            case "getAll":
//                getClients();
//                break;
            case "create":
                createClientTable();
                break;
            case "getSingle":
                getOneClient(request.split("/")[2]);
                break;
            case "insert":
                addClient(request.split("/")[2]);
                break;
            case "downloadInvoice":
                customerInvoice.downloadInvoice(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            case "getInvoices":
                customerInvoice.getInvoices(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            default:
                sendResponse("Please specify your request");
                break;
        }
    }

    public void createClientTable() {
        try{
            houseRepo.createClientTable();

            sendResponse("Client Table create successfully");
        }catch ( Exception e){}
    }

    public void addClient(String data) {
        try{
            House house=mapper.readValue(data,House.class);
            houseRepo.save(house);
            sendResponse(house.getMessage());

        }catch (Exception exception){ exception.printStackTrace();}
    }

    //login&profile
    public void getOneClient(String token){
        ResultSet resultSet=houseRepo.findByToken(token);
        House house=new House();

        try{
            if (!resultSet.next()){
                System.out.println("No such user");
            }else {
//            while(resultSet.next()){
                house.setId(resultSet.getInt(1));
                house.setName(resultSet.getString(2));
                house.setPin(resultSet.getString(3));
                house.setSectorId(resultSet.getString(4));
                house.setWalletId(resultSet.getString(5));
                sendResponse(mapper.writeValueAsString(house));
                System.out.println("House found " + mapper.writeValueAsString(house));
            }

        } catch (IOException | SQLException exception){
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
