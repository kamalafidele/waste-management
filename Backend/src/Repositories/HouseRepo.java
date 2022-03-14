package Repositories;

import Config.DatabaseConnection;
import Models.House;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseRepo {
    DatabaseConnection database;
    public HouseRepo(){
        database=new DatabaseConnection();
    }

//    public ResultSet findAll(){
//        return database.select("SELECT * FROM citizen");
//    }

    public ResultSet findByToken(String pin){
        return database.select("SELECT * FROM users WHERE pin = "+pin);
    }

    public void save(House house){
        String pin=house.genPin();
        //check if token exists
        ResultSet resultSet=database.select("SELECT * FROM users WHERE pin = "+pin);
        try {
            if(resultSet.next()){
                System.out.println("Pin already exists");
                pin=house.genPin();
                boolean query = database.insert("INSERT INTO users(name, email, Phone, pin, Role, Wallet, Location) VALUES ('"+house.getName()+"','"+house.getEmail()+"','"+house.getPhone()+"','"+pin+"','"+house.getRole()+"','"+house.getWallet()+"','"+house.getLocation()+"')");
                if(query){
                    house.setMessage("Citizen saved successfully");
                    System.out.println(house.getMessage());
                    System.out.println("Pin: "+pin);
                }
                else{
                    house.setMessage("Unable to save citizen");
                    System.out.println(house.getMessage());
                }
            }
            else{
                //insert
                boolean query = database.insert("INSERT INTO users(name, email, Phone, pin, Role, Wallet, Location) VALUES ('"+house.getName()+"','"+house.getEmail()+"','"+house.getPhone()+"','"+pin+"','"+house.getRole()+"','"+house.getWallet()+"','"+house.getLocation()+"')");
                if(query){
                    house.setMessage("Citizen saved successfully");
                    System.out.println(house.getMessage());
                    System.out.println("Pin: "+pin);
                }
                else{
                    house.setMessage("Unable to save citizen");
                    System.out.println(house.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
