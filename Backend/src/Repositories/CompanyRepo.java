package Repositories;

import Config.DatabaseConnection;
import Models.Company;

import java.sql.ResultSet;

public class CompanyRepo {
      DatabaseConnection database;
      public CompanyRepo(){
          database=new DatabaseConnection();
      }

      public ResultSet findAll(){
          return database.select("SELECT * FROM Company");
      }

      public ResultSet findById(int id){
          return database.select("SELECT * FROM Company WHERE id = "+id);
      }

      public void save(Company company){
          database.insert("INSERT INTO Company(name,email,paymentCode,password) VALUES ("+company.getName()+","+company.getEmail()+","+
                           company.getPaymentCode()+","+company.getPassword()+")");
      }
}
