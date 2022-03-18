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

            ResultSet answer = database.select("SELECT * FROM districts WHERE districtToken = '"+district.getDistrictToken()+"' AND password = '"+district.getPassword()+"' ");

            if(answer.next()){
                return true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
    public boolean save(District district){
        return database.insert("INSERT INTO districts(districtToken,districtName,password) VALUES ('"+district.getDistrictToken()+"','" +district.getDistrictName()+"','"+ district.getPassword()+"') ");
    }
    public ResultSet findAll(){
        return database.select("SELECT * FROM districts");
    }

}
