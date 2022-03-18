package Repositories;

import Config.DatabaseConnection;
import Models.Confirmer;


public class ConfirmerRepo {

    DatabaseConnection database;
    public ConfirmerRepo(){
        database=new DatabaseConnection();
    }

    public boolean save(Confirmer confirmer){
        return database.insert("INSERT INTO Users(name,email,phone,pin,Role) VALUES ('"+confirmer.getuserName()+"','"+confirmer.getEmail()+"','"+confirmer.getPhone()+"','" +confirmer.getPassword()+"','"+confirmer.getRole()+"')");
    }}
