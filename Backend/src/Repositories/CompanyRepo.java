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
          return database.select("SELECT * FROM Users WHERE Role = 2");
      }

      public ResultSet findById(long id){
              return database.select("SELECT * FROM Company WHERE Role = 2 AND  id = "+id);
      }

      public boolean save(Company company){
          return database.insert("INSERT INTO Users(name,email,phone,pin,Role) VALUES ('"+company.getName()+"','"
                  +company.getEmail()+"','"+ company.getPhone()+"','"+company.getPin()+"','"+company.getRole()+"')");
      }

      public boolean createContract (int districtId, int companyId){
         return database.insert("INSERT INTO District_Company (DistrictId,Company) VALUES ("+districtId+","+companyId+")");
      }
}
