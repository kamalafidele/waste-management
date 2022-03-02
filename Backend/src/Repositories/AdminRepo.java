package Repositories;

import Config.DatabaseConnection;
import Models.Admin;

import java.sql.ResultSet;

public class AdminRepo {
    private DatabaseConnection database;

    public AdminRepo(){
        this.database = new DatabaseConnection();
    }

    public boolean login(Admin admin){
        //login the admin
        ResultSet result = database.select("SELECT * FROM admin WHERE username = '"+admin.getUsername()+"' AND password = '"+admin.getPassword()+"' ");

        if(result != null){
            return true;
        }

        return false;
    }

}
