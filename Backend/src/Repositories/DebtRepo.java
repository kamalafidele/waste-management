package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class DebtRepo {
    public DatabaseConnection database;
    public DebtRepo(){database=new DatabaseConnection();}
    public ResultSet getBalance(long id){
        return database.select("SELECT  * FROM customer_wallets where user_id="+id);
    }
}
