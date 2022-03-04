package Repositories;

import Config.DatabaseConnection;
import Models.House;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseRepo {
    public String insertQuery = "INSERT INTO citizen(name, pin, sectorId, walletId) VALUES ('\"+house.getName()+\"','\"+house.getPin()+\"','\"+house.getSectorId()+\"','\"+house.getWalletId()+\"')";
    DatabaseConnection database;
    public HouseRepo(){
        database=new DatabaseConnection();
    }
//    id, name, pin, sectorId, walletId
    public void createClientTable(){
        String query="CREATE TABLE citizen(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(90) NOT NULL, pin BIGINT NOT NULL, sectorId INT NOT NULL, walletId INT NOT NULL, UNIQUE(pin), FOREIGN KEY(sectorId) REFERENCES Sector(id),FOREIGN KEY(walletId) REFERENCES Wallet(id));";
        database.createTable(query);
    }

//    public ResultSet findAll(){
//        return database.select("SELECT * FROM citizen");
//    }

    public ResultSet findByToken(String token){
        return database.select("SELECT * FROM citizen WHERE pin = "+token);
    }

    public void save(House house){
        String token=house.genPin();
        //check if token exists
        ResultSet resultSet=database.select("SELECT * FROM citizen WHERE pin = "+token);
        try {
            if(resultSet.next()){
                System.out.println("Token already exists");
                token=house.genPin();
                boolean query = database.insert(insertQuery);
                if(query){
                    house.setMessage("Citizen saved successfully");
                    System.out.println(house.getMessage());
                    System.out.println("Token: "+token);
                }
                else{
                    house.setMessage("Unable to save citizen");
                    System.out.println(house.getMessage());
                }
            }
            else{
                //insert
                boolean query = database.insert(insertQuery);
                if(query){
                    house.setMessage("Citizen saved successfully");
                    System.out.println(house.getMessage());
                    System.out.println("Token: "+token);
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
