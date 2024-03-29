package Repositories;

import Config.DatabaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtRepo {
    public DatabaseConnection database;
    public DebtRepo(DatabaseConnection database){
        this.database = database;
    }
    public ResultSet getBalance(String token){
         ResultSet result=database.select("select u.id,w.id as walletId,name,amount from users u inner join wallet w on u.wallet=w.id where pin="+token);
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
    public ResultSet getMySecurityDebt(String token){
        try {
            ResultSet result=database.select("select d.amount,d.month,d.userId,u.pin,s.service_name  from debt d inner join users u on u.id=d.userId inner join services s on s.id=d.service  WHERE Service_name='Security' and pin="+token);

            if(!result.next()){
                return null;
            }
            return  result;
        }
        catch (SQLException ie){
            ie.printStackTrace();
        }
        return null;
    }
    public ResultSet getMyWasteDebt(String token){
        try {
            ResultSet result=database.select("select d.amount,d.month,d.userId,u.pin,s.service_name  from debt d inner join users u on u.id=d.userId inner join services s on s.id=d.service  WHERE service_name='waste' and pin="+token);
//            while(result.next()){
//                return  result;
//            }
            if(!result.next()){
                return null;
            }
            return  result;
        }
        catch (SQLException ie){
            ie.printStackTrace();
        }
        return null;
    }
//    public ResultSet getAllDebtors(String service){
//        try {
//            ResultSet result=database.select("select userId, from debt");
//        }
//        catch (SQLException sql){
//            sql.printStackTrace();
//        }
//        return null;
//    }
}
