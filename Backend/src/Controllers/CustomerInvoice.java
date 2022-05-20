package Controllers;

import Models.CustomerInvoices;
import Repositories.CustomerInvoicesRepo;
import org.codehaus.jackson.map.ObjectMapper;


import java.sql.*;
import java.io.*;


public class CustomerInvoice {
    private CustomerInvoicesRepo customerInvoicesRepo;
    private ObjectMapper mapper;
    private DataOutputStream reply;
      public CustomerInvoice(){
       customerInvoicesRepo=new CustomerInvoicesRepo();
        mapper = new ObjectMapper();
    }



  
    public void getInvoice(int userId){
    CustomerInvoices  cInvoice = new CustomerInvoices();;
        ResultSet resultSet= customerInvoicesRepo.findById(userId);
        try{
          

            while(resultSet.next()){
              cInvoice.setInvoice_id((long) resultSet.getInt(1));
              cInvoice.setUserId(userId);
              cInvoice.setInvoice_date(resultSet.getDate("invoice_date"));
              cInvoice.setGeneration_time(resultSet.getTime(4));
              cInvoice.setService_paid(resultSet.getString(5));
              cInvoice.setAmount((long) resultSet.getInt(6));
            }

        sendResponse(mapper.writeValueAsString(cInvoice));

        }catch(IOException | SQLException exception ){}
    }



    public void filterRequest( String request, DataOutputStream reply ) {
        this.reply = reply;
        
        switch (request.split("/")[1]) {
            case "getInvoice":
                getInvoice(Integer.valueOf(request.split("/")[2]));
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
