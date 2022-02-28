package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class WalletsRepoHandler {
    DatabaseConnection database;
    public WalletsRepoHandler(){
        database=new DatabaseConnection();
    }
    public ResultSet findWalletByCompanyId(int companyId){
        return database.select("SELECT balance FROM customer_wallets WHERE company_id = "+companyId);
    }
    public ResultSet findWalletByDistrictId(int district_id){
        return database.select("SELECT balance FROM districts_wallets WHERE district_id = "+district_id);
    }
    public ResultSet findWalletByUserId(int userid){
        return database.select("SELECT balance FROM customer_wallets WHERE user_id = "+userid);
    }
    public ResultSet findWalletByAdminId(int admins_id){
        return database.select("SELECT balance FROM admins_wallet WHERE admins_id = "+admins_id);
    }
}
