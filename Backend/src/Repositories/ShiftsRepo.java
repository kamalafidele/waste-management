package Repositories;

import Config.DatabaseConnection;
import Models.Shifts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShiftsRepo {
    DatabaseConnection database;
    public ShiftsRepo(DatabaseConnection database){
        this.database = database;
    }

    public ResultSet findAll() throws SQLException {
//        return database.select("SELECT * FROM shifts");
        return database.select("SELECT shifts.id,shifts.company_id,shifts.date,shifts.Confirmer,company.name FROM shifts INNER JOIN company ON shifts.company_id = company.id");
    }

    public ResultSet findById(int id){
        return database.select("SELECT * FROM shifts where id = "+id);
    }

    public boolean save(Shifts shift){
        return database.insert("INSERT into shifts(company_id,date,Confirmer) VALUES ('"+shift.getCompany_id()+"','"
                +shift.getDate()+"','"+ shift.getConfirmerId()+"')");
    }
    public boolean initializeShift(){
        return database.insert("INSERT into shifts(status) VALUES ('on')");
    }
    public boolean closeShift(){
        return database.update("UPDATE shifts SET status = 'off' WHERE status = 'on'");
    }

}
