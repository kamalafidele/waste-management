package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRepo {
    private DatabaseConnection database;

    public LocationRepo(){
        this.database = new DatabaseConnection();
    }

    public int getLocationId(String location) throws SQLException {
        ResultSet result = database.select("SELECT id FROM locations WHERE Location_name = '"+location+"'");

        if(result.next()){
            System.out.println(result.getInt(1));
            return result.getInt(1);
        }

        return -1;
    }
}
