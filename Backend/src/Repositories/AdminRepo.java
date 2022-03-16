package Repositories;

import Config.DatabaseConnection;
import Models.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepo {
    private DatabaseConnection database;

    public AdminRepo(){
        this.database = new DatabaseConnection();
    }

    public boolean login(Admin admin){
        //login the admin
        try{
            PreparedStatement statement = database.connection.prepareStatement("SELECT * FROM users WHERE name = ? AND pin = ? AND Role = 1");
            statement.setString(1, admin.getName());
            statement.setLong(2, admin.getPassword());
            ResultSet result = statement.executeQuery();

            if(!result.next()){
                return  false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean createAdmin(Admin admin){

        boolean result = database.insert("INSERT INTO users(name, email, Phone, pin, Role, Location) VALUES('"+admin.getName()+"', '"+admin.getEmail()+"', '"+admin.getPhone()+"', "+admin.getPin()+", "+admin.getRole()+", "+admin.getLocation()+")");

        return result;
    }

}
