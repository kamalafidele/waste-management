package Repositories;

import Config.DatabaseConnection;

import java.sql.*;

public class PaymentRepo {
    DatabaseConnection database;
    public PaymentRepo(){
        database=new DatabaseConnection();
    }
    public ResultSet findDebt(int userId) {
        return database.select("SELECT * FROM wallets where user_id="+userId);
    }
}
