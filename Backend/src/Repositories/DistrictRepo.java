package Repositories;

import Config.DatabaseConnection;
import Models.District;

import java.sql.ResultSet;

public class DistrictRepo {
    private DatabaseConnection database;

    public DistrictRepo(){
        this.database = new DatabaseConnection();
    }

    public boolean login(District district) {
   
        try{

            ResultSet answer = database.select("SELECT * FROM districts WHERE districtToken = '"+district.getdistrictToken()+"' AND password = '"+district.getPassword()+"' ");

            if(answer.next()){
                return true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

}
