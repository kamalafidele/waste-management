package Repositories;

import Config.DatabaseConnection;
import Controllers.DebtController;
import Models.Company;
//import Models.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepo {
    DatabaseConnection database;
    Company company;
    public PaymentRepo(DatabaseConnection database){
        this.database = database;
    }
    DebtController debtController=new DebtController();
    public ResultSet findMomoAccountByNumber(String phoneNumber){
        return database.select("SELECT * FROM momoAccount where phoneNber = "+phoneNumber);
    }


    public void transferMoney(String phoneNumber, int amount, String token){

        // Reduce money from momoaccount table

        database.update("update momoaccount set balance = balance - " + amount + " where phoneNber = "+phoneNumber) ;

        // Increase  money to wallets table
        ResultSet resultSet = database.select("SELECT * FROM clients WHERE token=" + token);
        try{
            int UserId = 0;
            while(resultSet.next()){
                UserId = resultSet.getInt("id");
            }
            database.update("update customer_wallets set balance = balance + " + amount + " where user_Id = "+ UserId);
        }catch (SQLException exception){
            exception.printStackTrace();
        }



    }

    public ResultSet findById(long id){
        return database.select("SELECT * FROM Company WHERE id = "+id);
    }

    public boolean save(Company company){
        return database.insert("INSERT INTO Company(name,email,paymentCode) VALUES ('"+company.getName()+"','"+company.getEmail()+"')");
    }
    public ResultSet getBalance(long id){
        return database.select("SELECT  * FROM customer_wallets where user_id="+id);
    }
    public ResultSet getUserById(String token){return database.select("SELECT * FROM clients where token="+token);}
}
