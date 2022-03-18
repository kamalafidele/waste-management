package Controllers;

import Models.House;
import Repositories.HouseRepo;
import Controllers.CustomerInvoice;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        CustomerInvoice customerInvoice = new CustomerInvoice();
        switch (request.split("/")[1]) {
//            case "getAll":
//                getClients();
//                break;
            case "getSingle":
                getOneClient(request.split("/")[2]);
                break;
            case "insert":
                addClient(request.split("/")[2]);
                break;
            case "downloadInvoice":
//                customerInvoice.downloadInvoice(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            case "getInvoices":
                customerInvoice.getInvoice(Integer.parseInt(request.split("/")[2]));
                break;
            default:
                sendResponse("Please specify your request");
                break;
        }
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
                house.setEmail(resultSet.getString(3));
                house.setPhone(resultSet.getString(4));
                house.setPin(BigInteger.valueOf(resultSet.getInt(5)));
                house.setRole(resultSet.getInt(6));
                house.setWallet(resultSet.getInt(7));
                house.setLocation(resultSet.getInt(8));
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
