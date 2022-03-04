package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class WalletsRepoHandler {
    DatabaseConnection database;
    public WalletsRepoHandler(){
        database=new DatabaseConnection();
    }
    public ResultSet findWalletByCompanyId(int companyId){

        return database.select("select amount from wallet w inner join company c on a.walletId=w.id where c.id= "+companyId);
    }
    public ResultSet findWalletByDistrictId(int district_id){
        return database.select("select amount from wallet w inner join district d on d.walletId=w.id where d.id="+district_id);
    }
    public ResultSet findWalletByUserId(int userid){
        return database.select("select amount from wallet w inner join citizen c on c.walletId=w.id where c.id"+userid);
    }
    public ResultSet findWalletByAdminId(int admins_id){
        return database.select("select amount from wallet w inner join admin a on a.walletId=w.id where a.id = "+admins_id);
    }
}
