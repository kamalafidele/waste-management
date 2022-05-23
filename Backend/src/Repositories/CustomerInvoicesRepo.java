package Repositories;



import Config.DatabaseConnection;
import Models.CustomerInvoices;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerInvoicesRepo {
    private DataOutputStream toClient;

    DatabaseConnection database;
    public CustomerInvoicesRepo(DatabaseConnection database){
        this.database = database;
    }
    public ResultSet findById(int userId){
        return database.select("SELECT * FROM invoices WHERE User = "+userId);
    }

    // THIS A METHOD FOR SENDING
    public void sendResponse( String response ) throws IOException, SQLException {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }

    }

    public void downloadInvoice(int invoice_id, DataOutputStream client) throws Exception {
        this.toClient = client;
        System.out.println("Downloading invoice "+invoice_id);
        DatabaseConnection con = new DatabaseConnection();
        File downloads = new File("/home/bijoux/Desktop/DATA/projects/waste_management/waste-management/Backend/src/Invoices/");
        if(!downloads.exists()) {
            downloads.mkdir();
        }
        File myFile = new File("/home/bijoux/Desktop/DATA/projects/waste_management/waste-management/Backend/src/Invoices/"+invoice_id+".txt");

        if(myFile.createNewFile() || myFile.exists()) {
            ResultSet set = con.getById(" "+invoice_id+";");
                while(set.next()){
                    String invoice = "| ---------------- Invoice ---------------- |\n\n";
                    invoice += "|   No      :           " + set.getString("id") + "\n";
                    invoice += "|   Service :           " + set.getString("Service") + "\n";
                    invoice += "|   Amount  :           " + set.getInt("Amount") + "\n";
                    invoice += "|   Time    :           " + set.getTime("currentTime") + "\n\n";
                    invoice += "| ----------- Done on " + set.getDate("Date") + " ---------- |";

                    FileWriter myWriter = new FileWriter("/home/bijoux/Desktop/DATA/projects/waste_management/waste-management/Backend/src/Invoices/"+invoice_id+".txt");
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
        ResultSet set = con.getById("select i.id, u.name, s.Service_name, i.Amount, i.Date, i.currentTime from users u join invoices i ON i.User = u.id JOIN services " +
                "s on i.Service = s.id WHERE i.User = " + userId+";");

        try {
            if(!set.next()){
                response = "There is no invoice for the user!";
                sendResponse(response);
            }else{
                CustomerInvoices invoiced = null;
                res += "|     "+set.getInt("id")+"      |      "+set.getString("name")+"      | "+set.getString("Service_name")+"  |    "
                        +set.getInt("Amount")+ " |   "+set.getDate("Date")+"   |  "+ set.getTime("currentTime")+"         |\n";

                while(set.next()){
                    res += "|     "+set.getInt("id")+"      |      "+set.getString("name")+"      | "+set.getString("Service_name")+"  |   "
                            +set.getInt("Amount")+ "  |   "+set.getDate("Date")+"   |  "+ set.getTime("currentTime")+"         |\n";
                }
                sendResponse(res);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

}
