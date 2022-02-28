package Repositories;



import Config.DatabaseConnection;
import Models.CustomerInvoices;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerInvoicesRepo {
    private DataOutputStream toClient;

    DatabaseConnection database;
    public customerInvoicesRepo(){
        database=new DatabaseConnection();
    }
    public ResultSet findById(int userId){
        return database.select("SELECT * FROM CustomerInvoices WHERE userid = "+userId);
    }

    // THIS A METHOD FOR SENDING
    public void sendResponse( String response ) throws IOException, SQLException {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }

//    public void save(CustomerInvoices newInvoice){
//        database.insert("INSERT INTO CustomerInvoices(userId,invoice_date,generation_time,service_paid,amount) " +
//                "VALUES ("+newInvoice.getUserId()+","+ newInvoice.getInvoice_date()+","+newInvoice.getGeneration_time()+","+
//                newInvoice.getService_paid()+","+newInvoice.getAmount()+")");
    }

    public void downloadInvoice(int invoice_id, DataOutputStream client) throws Exception {
        this.toClient = client;
        System.out.println("Downloading invoice "+invoice_id);
        DatabaseConnection con = new DatabaseConnection();
        File downloads = new File("./downloads");
        if(!downloads.exists()) {
            downloads.mkdir();
        }
        File myFile = new File("./downloads/"+invoice_id+".txt");

        if(myFile.createNewFile() || myFile.exists()) {
            ResultSet set = con.getById("SELECT * from customerinvoices where invoice_id = 1;");
                while(set.next()){
                    String invoice = "| ---------------- Invoice ---------------- |\n\n";
                    invoice += "|   No      :           " + set.getString("invoice_id") + "\n";
                    invoice += "|   Service :           " + set.getString("service_paid") + "\n";
                    invoice += "|   Amount  :           " + set.getInt("amount") + "\n";
                    invoice += "|   Time    :           " + set.getTime("generation_time") + "\n\n";
                    invoice += "| ----------- Done on " + set.getDate("invoice_date") + " ---------- |";

                    FileWriter myWriter = new FileWriter("./downloads/" + invoice_id + ".txt");
                    myWriter.write(invoice);
                    myWriter.close();
                    toClient.writeUTF("Finished downloading invoice " + invoice_id + " !!");
                }

                if (!set.next()) {
                    sendResponse("Invoice not found");
                }
        }
    }

    public void getInvoices(int userId, DataOutputStream client) throws IOException, SQLException {
        this.toClient = client;
        List<CustomerInvoices> invoices= new ArrayList<>();
        DatabaseConnection con = new DatabaseConnection();

        String res = "";
        String response = null;
        ResultSet set = con.getById("SELECT * from customerinvoices where userId = "+userId+";");
        try {
            if(!set.next()){
                response = "There is no invoice for the user!";
                sendResponse(response);
            }else{
                CustomerInvoices invoiced = null;
                res += "|     "+set.getInt("invoice_id")+"      |      "+set.getInt("userid")+"      | "+set.getString("service_paid")+"  |    "
                        +set.getInt("amount")+ " |   "+set.getDate("invoice_date")+"   |  "+ set.getTime("generation_time")+"         |\n";

                while(set.next()){
                    res += "|     "+set.getInt("invoice_id")+"      |      "+set.getInt("userid")+"      | "+set.getString("service_paid")+"  |   "
                            +set.getInt("amount")+ "  |   "+set.getDate("invoice_date")+"   |  "+ set.getTime("generation_time")+"         |\n";
                }
                sendResponse(res);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

}
