package Repositories;

import Config.DatabaseConnection;
import Models.Company;
import Models.District;

import java.sql.ResultSet;

public class DistrictRepo {
    private DatabaseConnection database;

    public DistrictRepo(){
        this.database = new DatabaseConnection();
    }

    public boolean login(District district) {
   
        try{

            ResultSet answer = database.select("SELECT * FROM admin WHERE username = '"+district.getEmail()+"' AND password = '"+district.getPin()+"' ");

            if(answer.next()){
                return true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
    public boolean save(District district){
        return database.insert("INSERT INTO Users(name,email,phone,pin,Role) VALUES ('"+district.getName()+"','"
                +district.getEmail()+"','"+ district.getPhone()+"','"+district.getPin()+"','"+district.getRole()+"') ");
    }
    public ResultSet findAll(){
        return database.select("SELECT * FROM Users WHERE Role = 2");
    }

}
