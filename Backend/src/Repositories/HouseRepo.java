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
        String query="CREATE TABLE IF NOT EXISTS clients(id INTEGER PRIMARY KEY AUTO_INCREMENT,fullnames VARCHAR(255),nid VARCHAR(255),houseno VARCHAR(255),telno VARCHAR(255),sector VARCHAR(255),cell VARCHAR(255),village VARCHAR(255),token VARCHAR(255))";
        database.createTable(query);
    }

//    public ResultSet findAll(){
//        return database.select("SELECT * FROM clients");
//    }

    public ResultSet findByToken(String token){
        return database.select("SELECT * FROM clients WHERE token = "+token);
    }

    public void save(House house){
        String token=house.getToken();
        //check if token exists
        ResultSet resultSet=database.select("SELECT * FROM clients WHERE token = "+token);
        try {
            if(resultSet.next()){
                System.out.println("Token already exists");
                token=house.getToken();
                database.insert("INSERT INTO clients(fullnames,nid,houseno,telno,sector,cell,village,token) VALUES ("+house.getFullnames()+","+house.getNid()+","+house.getHouseno()+","+house.getTelno()+","+house.getSector()+","+house.getCell()+","+house.getVillage()+","+token+")");
                System.out.println("House saved");
                System.out.println("Token: "+token);
            }
            else{
                //insert
                database.insert("INSERT INTO clients(fullnames,nid,houseno,telno,sector,cell,village,token) VALUES ("+house.getFullnames()+","+house.getNid()+","+house.getHouseno()+","+house.getTelno()+","+house.getSector()+","+house.getCell()+","+house.getVillage()+","+token+")");
                System.out.println("House saved");
                System.out.println("Token: "+token);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
