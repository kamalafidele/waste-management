package Repositories;

import Config.DatabaseConnection;
import Models.User;

import java.sql.ResultSet;

public class UserRepo {
    private DatabaseConnection connection;

    public UserRepo(DatabaseConnection connection) {
        this.connection = connection;
    }

    public boolean save(User user) {
        return connection.insert(
                "INSERT INTO users (name, email, phone, password, role, wallet, location, work_at)"
             + " VALUES ('" + user.getName() + "','" + user.getEmail() + "','" + user.getPhone() + "'," + user.getRole() + ","
             + user.getWallet() + "," + user.getLocation() + "," + user.getWork_at() + ")" );
    }

    public ResultSet findById(long id) {
        return connection.select("SELECT * FROM users WHERE id = " + id);
    }

    public ResultSet findAll() {
        return connection.select("SELECT * FROM users u INNER JOIN roles r ON u.id = r.id");
    }

    public ResultSet findByEmailAndPassword(String email, String password) {
        return connection.select("SELECT * FROM users WHERE email = '" + email + "' AND '" + password + "'");
    }
}
