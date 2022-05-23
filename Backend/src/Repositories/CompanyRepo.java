package Repositories;

import Config.DatabaseConnection;
import Models.Company;

import java.sql.ResultSet;

public class CompanyRepo {
      DatabaseConnection database;
      public CompanyRepo(DatabaseConnection database) {
          this.database = database;
      }

      public ResultSet findAll() {
          return database.select("SELECT * FROM company");
      }

      public ResultSet findById(long id) {
          return database.select("SELECT * FROM company WHERE id = "+id);
      }

      public boolean save(Company company) {
          return database.insert("INSERT INTO company(name,TIN,email,wallet) VALUES ('"+company.getName()+"','"
                  +company.getTin() + "','" + company.getEmail()+"'," + company.getWalletId() +")");
      }


      public boolean createContract (int districtId, int companyId) {
         return database.insert("INSERT INTO district_company (district_id,company_id) VALUES ("+districtId+","+companyId+")");
      }

}