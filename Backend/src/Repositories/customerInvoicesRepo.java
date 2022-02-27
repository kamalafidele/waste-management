package Repositories;



import Config.DatabaseConnection;
import Models.CustomerInvoices;

import java.sql.ResultSet;

public class customerInvoicesRepo {

    DatabaseConnection database;
    public customerInvoicesRepo(){
        database=new DatabaseConnection();
    }
    public ResultSet findAll(){
        return database.select("SELECT * FROM Company");
    }

    public ResultSet findById(int id){
        return database.select("SELECT * FROM Company WHERE id = "+id);
    }

    public void save(CustomerInvoices newInvoice){
        database.insert("INSERT INTO CustomerInvoices(userId,invoice_date,generation_time,service_paid,amount) " +
                "VALUES ("+newInvoice.getUser_id()+","+ newInvoice.getInvoice_date()+","+newInvoice.getGeneration_time()+","+
                newInvoice.getService_paid()+","+newInvoice.getAmount()+")");
    }
}
