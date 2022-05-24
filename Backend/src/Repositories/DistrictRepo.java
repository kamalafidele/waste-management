package Repositories;

import Config.DatabaseConnection;
import Models.Company;
import Models.District;

import javax.xml.crypto.Data;
import java.sql.ResultSet;

public class DistrictRepo {
    private DatabaseConnection database;

    public DistrictRepo(DatabaseConnection database){
        this.database = database;
    }

    public boolean save(District district){
        return database.insert("INSERT INTO district(name,email,wallet) VALUES ('"+district.getName()+"','" +district.getEmail()+"',"
        + district.getWalletId() + ")");
    }

    public ResultSet findAll(){
        return database.select("SELECT * FROM district");
    }

}
