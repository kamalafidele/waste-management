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

      public ResultSet findById(long id){
          return database.select("SELECT * FROM Company WHERE id = "+id);
      }

      public boolean save(Company company){
          return database.insert("INSERT INTO Company(name,email,paymentCode) VALUES ('"+company.getName()+"','"+company.getEmail()+"','"+ company.getPaymentCode()+"')");
      }
}
