package Repositories;

import Config.DatabaseConnection;
import Models.Shifts;

import java.sql.ResultSet;

public class ShiftsRepo {
    DatabaseConnection database;
    public ShiftsRepo(){
        database=new DatabaseConnection();
    }

    public ResultSet findAll(){
        return database.select("SELECT * FROM shifts");
    }

    public ResultSet findById(int id){
        return database.select("SELECT * FROM shifts where id = "+id);
    }

    public boolean save(Shifts shift){
        return database.insert("INSERT into shifts(Company_id,Date,Confirmer) VALUES ('"+shift.getCompany_id()+"','"
                +shift.getDate()+"','"+ shift.getConfirmerId()+"')");
    }


}
