package Repositories;

import Config.DatabaseConnection;
import Models.District;

import java.sql.ResultSet;

public class DistrictDemo {
    private DatabaseConnection database;

    public DistrictDemo(){
        this.database = new DatabaseConnection();
    }

    public boolean login(District district) {
        //login the admin
        try{

            ResultSet result = database.select("SELECT * from districts WHERE districtToken"=district.getdistrictToken());
            if(result.next()){
                return true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

}
