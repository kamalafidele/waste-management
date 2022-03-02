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

    public void createClientTable(){
        String query="CREATE TABLE IF NOT EXISTS clients(id INTEGER PRIMARY KEY AUTO_INCREMENT,fullnames VARCHAR(255) not null unique,nid VARCHAR(255) unique,houseno VARCHAR(255),telno VARCHAR(255) unique,sector VARCHAR(255),cell VARCHAR(255),village VARCHAR(255),token VARCHAR(255) not null unique)";
        database.createTable(query);
    }

//    public ResultSet findAll(){
//        return database.select("SELECT * FROM clients");
//    }

    public ResultSet findByToken(String token){
        return database.select("SELECT * FROM clients WHERE token = "+token);
    }

//    public ResultSet findByNames(String names){
//        return database.select("SELECT * FROM clients WHERE token = "+names);
//    }

    public void save(House house){
        String token=house.genPin();
        //check if token exists
        ResultSet resultSet=database.select("SELECT * FROM clients WHERE token = "+token);
        try {
            if(resultSet.next()){
                System.out.println("Token already exists");
                token=house.genPin();
                boolean query = database.insert("INSERT INTO clients(fullnames,nid,houseno,telno,sector,cell,village,token) VALUES ('"+house.getFullnames()+"','"+house.getNid()+"','"+house.getHouseno()+"','"+house.getTelno()+"','"+house.getSector()+"','"+house.getCell()+"','"+house.getVillage()+"','"+token+"')");
                if(query){
                    house.setMessage("Client saved successfully");
                    System.out.println(house.getMessage());
                    System.out.println("Token: "+token);
                }
                else{
                    house.setMessage("Unable to save client");
                    System.out.println(house.getMessage());
                }
            }
            else{
                //insert
                boolean query = database.insert("INSERT INTO clients(fullnames,nid,houseno,telno,sector,cell,village,token) VALUES ('"+house.getFullnames()+"','"+house.getNid()+"','"+house.getHouseno()+"','"+house.getTelno()+"','"+house.getSector()+"','"+house.getCell()+"','"+house.getVillage()+"','"+token+"')");
                if(query){
                    house.setMessage("Client saved successfully");
                    System.out.println(house.getMessage());
                    System.out.println("Token: "+token);
                }
                else{
                    house.setMessage("Unable to save client");
                    System.out.println(house.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
