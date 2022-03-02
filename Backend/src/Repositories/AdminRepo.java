package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class AdminRepo {
    private DatabaseConnection database;

    public AdminRepo(){
        this.database = new DatabaseConnection();
    }

    public boolean login(String username, String password){
        //login the admin
        ResultSet result = database.select("SELECT * FROM admin WHERE username = " + username + " AND password = " + password);
        System.out.println(result);
        return false;
    }

}
