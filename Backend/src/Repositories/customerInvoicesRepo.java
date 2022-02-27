package Repositories;



import Config.DatabaseConnection;
import Models.CustomerInvoices;

import java.sql.ResultSet;

public class customerInvoicesRepo {

    DatabaseConnection database;
    public customerInvoicesRepo(){
        database=new DatabaseConnection();
    }
    public ResultSet findById(int userId){
        return database.select("SELECT * FROM CustomerInvoices WHERE userid = "+userId);
    }

    public void save(CustomerInvoices newInvoice){
        database.insert("INSERT INTO CustomerInvoices(userId,invoice_date,generation_time,service_paid,amount) " +
                "VALUES ("+newInvoice.getUser_id()+","+ newInvoice.getInvoice_date()+","+newInvoice.getGeneration_time()+","+
                newInvoice.getService_paid()+","+newInvoice.getAmount()+")");
    }
}
