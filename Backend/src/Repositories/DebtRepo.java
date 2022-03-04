package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtRepo {
    public DatabaseConnection database;
    public DebtRepo(){database=new DatabaseConnection();}
    public ResultSet getBalance(String token){
         ResultSet result=database.select("SELECT  * FROM clients where token="+token);
         int userId=0;
         try {
             while(result.next()){
                 userId=result.getInt(3);
             }
             ResultSet balance=database.select("Select * From customer_wallets where user_id="+userId);
             return balance;
         }
         catch (SQLException sql){
             sql.printStackTrace();
         }
         return null;
    }
}
