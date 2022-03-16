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
        try{
            ResultSet result = database.select("SELECT * FROM user WHERE name = '"+admin.getName()+"' AND pin = '"+admin.getPassword()+"' AND Role = 1 ");
            if(result.next()){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean createAdmin(Admin admin){

        boolean result = database.insert("INSERT INTO users(name, email, Phone, pin, Role, Location) VALUES('"+admin.getName()+"', '"+admin.getEmail()+"', '"+admin.getPhone()+"', "+admin.getPin()+", "+admin.getRole()+", "+admin.getLocation()+")");

        return result;
    }

}
