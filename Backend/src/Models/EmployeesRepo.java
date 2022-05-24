package Models;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class EmployeesRepo {
    private DatabaseConnection connection;
    public EmployeesRepo(DatabaseConnection connection){
        this.connection=connection;
    }
    public ResultSet employees(){
        return connection.select("select * from users");
    }
}
