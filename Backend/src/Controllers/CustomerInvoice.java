package Controllers;

import Models.CustomerInvoices;
import Repositories.customerInvoicesRepo;
import org.codehaus.jackson.map.ObjectMapper;


import java.sql.*;
import java.io.*;


public class CustomerInvoice {
    private customerInvoicesRepo customerInvoicesRepo;
    private ObjectMapper mapper;
    private DataOutputStream reply;
      public CustomerInvoice(){
       customerInvoicesRepo=new customerInvoicesRepo();
        mapper = new ObjectMapper();
    }



  
    public void getInvoice(int userId){
    CustomerInvoices  cInvoice = new CustomerInvoices();;
        ResultSet resultSet= customerInvoicesRepo.findById(userId);
//        try{
//
//
//            while(resultSet.next()){
//              cInvoice.setInvoice_id(resultSet.getInt(1));
//              cInvoice.setUser_id(userId);
//              cInvoice.setInvoice_date(resultSet.getString("invoice_date"));
//              cInvoice.setGeneration_time(resultSet.getString(4));
//              cInvoice.setService_paid(resultSet.getString(5));
//              cInvoice.setAmount(resultSet.getInt(6));
//            }
//
//        sendResponse(mapper.writeValueAsString(cInvoice));
//
//        }catch( SQLException exception ){}
    }

    public void saveInvoice(String data){
   try{
            CustomerInvoices cInvoice = mapper.readValue(data,CustomerInvoices.class);
            customerInvoicesRepo.save(cInvoice);

            sendResponse("Recorded invoice");
        }catch (IOException exception){}
    }

    public void filterRequest( String request, DataOutputStream reply ) {
        this.reply = reply;
        
        switch (request.split("/")[1]) {
            case "getInvoice":
                getInvoice(Integer.valueOf(request.split("/")[2]));
              break;
            case "createInvoice":
               saveInvoice(request.split("/")[2]);
              break;
            default:
                sendResponse("Please specify your request");
              break;
        }
    }

    public void sendResponse( String response ) {
        try {
            reply.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
