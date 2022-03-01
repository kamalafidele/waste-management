package Repositories;

import Config.DatabaseConnection;
import Models.Company;

import java.sql.ResultSet;

public class PaymentRepo {
    DatabaseConnection database;
    public PaymentRepo(){
        database=new DatabaseConnection();
    }

    public ResultSet findMomoAccountByNumber(int phoneNumber){
        return database.select("SELECT * FROM momoAccount where phoneNber = "+phoneNumber);
    }

    public ResultSet findById(long id){
        return database.select("SELECT * FROM Company WHERE id = "+id);
    }

    public boolean save(Company company){
        return database.insert("INSERT INTO Company(name,email,paymentCode) VALUES ('"+company.getName()+"','"+company.getEmail()+"','"+ company.getPaymentCode()+"')");
    }
}
