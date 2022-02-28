package Repositories;

import Config.DatabaseConnection;

import java.sql.*;

public class PaymentRepo {
    DatabaseConnection database;
    public PaymentRepo(){
        database=new DatabaseConnection();
    }
    public ResultSet findDebt(){
        return database.select("select * from wallet where amount<0");
    }
}
