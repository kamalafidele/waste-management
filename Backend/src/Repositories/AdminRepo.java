package Repositories;

import Config.DatabaseConnection;
import Models.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepo {
    private DatabaseConnection database;
    private ResultSet result;

    public AdminRepo(){
        this.database = new DatabaseConnection();
    }

    public int login(Admin admin) throws SQLException {
        //login the admin
        try{
            PreparedStatement statement = database.connection.prepareStatement("SELECT id FROM users WHERE name = ? AND pin = ? AND Role = 1");
            statement.setString(1, admin.getName());
            statement.setLong(2, admin.getPassword());

            this.result = statement.executeQuery();

        }catch (Exception e) {
            e.printStackTrace();
        }

        if(result.next()){
            int id = result.getInt("id");
            return id;
        }else{
            return 0;
        }

    }

    public boolean createAdmin(Admin admin){
        return database.insert("INSERT INTO users(name, email, Phone, pin, Role, Location) VALUES('"+admin.getName()+"', '"+admin.getEmail()+"', '"+admin.getPhone()+"', "+admin.getPin()+", "+admin.getRole()+", "+admin.getLocation()+")");
    }

}
