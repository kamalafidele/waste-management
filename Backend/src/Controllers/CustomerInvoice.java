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
        String res = "";
        String response = null;
        CustomerInvoices  cInvoice = new CustomerInvoices();;
        ResultSet set= customerInvoicesRepo.findById(userId);

        try {
            if(!set.next()){
                response = "There is no invoice for the user!";
                sendResponse(response);
            }else{
                res += "|     "+set.getInt("id")+"      |      "+set.getString("name")+"      | "+set.getString("Service_name")+"  |    "
                        +set.getInt("Amount")+ " |   "+set.getDate("Date")+"   |  "+ set.getTime("currentTime")+"         |\n";

                while(set.next()){
                    res += "|     "+set.getInt("id")+"      |      "+set.getString("name")+"      | "+set.getString("Service_name")+"  |   "
                            +set.getInt("Amount")+ "  |   "+set.getDate("Date")+"   |  "+ set.getTime("currentTime")+"         |\n";
                }
                sendResponse(res);
            }
        }catch (NullPointerException | SQLException e){
            e.printStackTrace();
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
