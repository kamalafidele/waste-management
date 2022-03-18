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
    public PaymentRepo(){
        database=new DatabaseConnection();
    }
    DebtController debtController=new DebtController();
    public ResultSet findMomoAccountByNumber(String phoneNumber){
        return database.select("SELECT * FROM momoAccounts where phoneNber = "+phoneNumber);
    }
    public ResultSet findBankAccount(int bankAcc){
        return database.select("SELECT * FROM bankAccounts where accNber = "+bankAcc);
    }

    public void recordTransaction(int type, int amount){
        database.insert("INSERT INTO transactions(Type, Amount) values("  +type+","+amount+")");

    }

    public void transferMoney(String phoneNumber, int amount, String token){

        // Reduce money from momoaccount table

        database.update("update momoAccounts set balance = balance - " + amount + " where phoneNber = "+ phoneNumber) ;

        // Increase  money to wallets table
        ResultSet resultSet = database.select("SELECT * FROM users WHERE Phone=" + phoneNumber);
        try{

            int walletId = 0;
            while(resultSet.next()){
                walletId = resultSet.getInt("Wallet");

            }
            database.update("update wallet set amount = amount + " + amount+" where id = "+ walletId);
        }catch (SQLException exception){
            exception.printStackTrace();
        }



    }
    public void transferFunds(int accNber,int amount,String token){
        // Reduce money from bankaccount table
        database.update("update bankAccounts set balance = balance - " + amount + " where accNber = "+accNber) ;

        // Increase  money to wallets table
        ResultSet resultSet = database.select("SELECT * FROM users WHERE pin=" + token);
        try{
            int walletId = 0;
            while(resultSet.next()){

                walletId = resultSet.getInt("Wallet");
                System.out.println(walletId);
            }
//            database.update("update customer_wallets set balance = balance + " + amount + " where user_Id = "+ UserId);
            database.update("update wallet set amount = amount +"+amount+" where id ="+ walletId);
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
        return database.select("SELECT  * FROM wallets where id="+id);
    }
    public ResultSet getUserById(String token){return database.select("SELECT * FROM citizen where pin="+token);}
}
