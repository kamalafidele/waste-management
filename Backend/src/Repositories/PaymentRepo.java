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
    DebtController debtController;

    public PaymentRepo(DatabaseConnection database){
        this.database = database;
        this.debtController = new DebtController(database);
    }

    public ResultSet findMomoAccountByNumber(String phoneNumber){
        return database.select("SELECT * FROM momoaccount where phoneNber = "+phoneNumber);
    }
    public ResultSet findbankAccount(String bankNumber){
        return database.select("SELECT * FROM bankaccount where bankacc = "+bankNumber);
    }


    public void transferMoney(String phoneNumber, int amount){

        // Reduce money from momoaccount table

        database.update("update momoaccount set balance = balance - " + amount + " where phoneNber = "+phoneNumber) ;

        // Increase  money to wallets table
        ResultSet resultSet = database.select("SELECT * FROM users WHERE phone=" + phoneNumber);
        try{
            int WalletId = 0;
            while(resultSet.next()){
                WalletId = resultSet.getInt("wallet");
                System.out.println(WalletId);
            }
            database.update("update wallet set amount = amount + " + amount + " where id = "+ WalletId);
        }catch (SQLException exception){
            exception.printStackTrace();
        }



    }
    public void transferFunds(String bankNumber, int amount, String token){

        // Reduce money from bankaccount table

        database.update("update bankaccount set balance = balance - " + amount + " where bankacc = "+bankNumber) ;

        // Increase  money to wallets table
        ResultSet resultSet = database.select("SELECT * FROM users WHERE token=" + token);
        try{
            int WalletId = 0;
            while(resultSet.next()){
                WalletId = resultSet.getInt("wallet");
                System.out.println(WalletId);
            }
            database.update("update wallet set amount = amount + " + amount + " where id = "+ WalletId);
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
        return database.select("SELECT  * FROM wallet where user_id="+id);
    }
    public ResultSet getUserById(String token){return database.select("SELECT * FROM users where token="+token);}
}
