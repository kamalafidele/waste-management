package Repositories;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Config.DatabaseConnection;

import Models.Admin;
import Models.ServiceConfirmation;

import java.sql.ResultSet;

public class ServiceConfirmationRepo {
    DatabaseConnection database;

    public ServiceConfirmationRepo(DatabaseConnection database) {
        this.database = database;
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

//    public ResultSet confirmerLogin(String email, Integer pin) {
//        String query = "select * from users where role='confirmer' AND email =" + email + "AND pin= " + pin;
//        return database.select(query);
//    }
public boolean login(Admin confirmer){
    //login the admin
    try{
        PreparedStatement statement = database.connection.prepareStatement("SELECT * FROM users WHERE email = ? AND pin = ? AND Role = 4");
        statement.setString(1, confirmer.getEmail());
        statement.setLong(2, confirmer.getPin());
        ResultSet result = statement.executeQuery();

        if(!result.next()){
            return  false;
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
    return true;
}

public ResultSet findCompanyId(String name){return database.select("SELECT id from users where Role =2 and name="+name);}
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