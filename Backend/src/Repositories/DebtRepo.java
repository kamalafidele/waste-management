package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtRepo {
    public DatabaseConnection database;
    public DebtRepo(){database=new DatabaseConnection();}
    public ResultSet getBalance(String token){
         ResultSet result=database.select("SELECT  * FROM users where pin="+token);
         int walletId=0;
         try {
             while(result.next()){
                 walletId=result.getInt("Wallet");
             }
             ResultSet balance=database.select("Select * From wallet where id="+walletId);
             return balance;
         }
         catch (SQLException sql){
             sql.printStackTrace();
         }
         return null;
    }
}
