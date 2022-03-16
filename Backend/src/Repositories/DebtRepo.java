package Repositories;

import Config.DatabaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtRepo {
    public DatabaseConnection database;
    public DebtRepo(){database=new DatabaseConnection();}
    public ResultSet getBalance(String token){
         ResultSet result=database.select("select c.id,w.id as walletId,name,amount from citizen c inner join wallet w on c.walletId=w.id where pin="+token);
         try {
             while(result.next()){
                 return  result;
             }
         }
         catch (SQLException sql){
             sql.printStackTrace();
         }
         return null;
    }
    public ResultSet getMyDebt(String token){
        try {
            ResultSet result=database.select("select amount,month,userId,pin  from debt inner join citizen on citizen.id=debt.userId  WHERE pin="+token);
            while(result.next()){
                return  result;
            }
        }
        catch (SQLException ie){
            ie.printStackTrace();
        }
        return null;
    }
}
