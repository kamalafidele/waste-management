package Repositories;
import java.sql.SQLException;
import Config.DatabaseConnection;

import Models.ServiceConfirmation;

import java.sql.ResultSet;

public class ServiceConfirmationRepo {
    DatabaseConnection database;

    public ServiceConfirmationRepo() {
        database = new DatabaseConnection();
    }

    public ResultSet findAll() {
        return database.select("SELECT * FROM service_confirmation");
    }

    public ResultSet findById(int id) {
        return database.select("SELECT * FROM service_confirmation where id = " + id);
    }

    public boolean save(ServiceConfirmation confirmservice) {
        return database.insert("INSERT into service_confirmation(Service,shift_id,user_id) VALUES ('" + confirmservice.getServiceId() + "','"
                + confirmservice.getShiftId() + "','" + confirmservice.getConfirmerId() + "')");
    }

    public ResultSet confirmerLogin(String email, Integer pin) {
        String query = "select * from users where role='confirmer' AND email =" + email + "AND pin= " + pin;
        return database.select(query);
    }

    public boolean validate(String company, int confirmerId) throws SQLException {
        try {
            String query = "select id,name,Location from users where name =" + company + "And Location =" +
                    "select Location from users where id =" + confirmerId;
            ResultSet data = database.select(query);
            if (data.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

}