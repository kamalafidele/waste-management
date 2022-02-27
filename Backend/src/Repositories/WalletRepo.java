package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class WalletRepo {
    DatabaseConnection database;
    public WalletRepo(){
        database=new DatabaseConnection();
    }
    public ResultSet findWalletById(int userid){
        return database.select("SELECT balance FROM Company WHERE userId = "+userid);
    }
}
