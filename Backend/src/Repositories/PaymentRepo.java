package Repositories;

import Config.DatabaseConnection;
import Models.Company;

import java.sql.ResultSet;

public class PaymentRepo {
    DatabaseConnection database;
    public PaymentRepo(){
        database=new DatabaseConnection();
    }

    public ResultSet findMomoAccountByNumber(String phoneNumber){
        return database.select("SELECT * FROM momoAccount where phoneNber = "+phoneNumber);
    }


    public void transferMoney(String phoneNumber, int amount, int userId){

        // Reduce money from momoaccount table

        database.update("update momoaccount set balance = balance - " + amount + " where phoneNber = "+phoneNumber) ;

        // Increase  money to wallets table

        database.update("update customer_wallets set balance = balance + " + amount + " where user_Id = "+ userId) ;


    }

    public ResultSet findById(long id){
        return database.select("SELECT * FROM Company WHERE id = "+id);
    }

    public boolean save(Company company){
        return database.insert("INSERT INTO Company(name,email,paymentCode) VALUES ('"+company.getName()+"','"+company.getEmail()+"','"+ company.getPaymentCode()+"')");
    }
}
