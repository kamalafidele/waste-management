package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;

public class WalletsRepoHandler {
    DatabaseConnection database;
    public WalletsRepoHandler(DatabaseConnection database){
        this.database = database;
    }
    public ResultSet  createWallet(Long user_id) {
        database.insert("INSERT INTO WALLET (user_id) VALUES("+ user_id +")");
        return database.select("select id from wallet ORDER BY id DESC LIMIT 1;");
    }
    public ResultSet findWalletByCompanyId(int companyId){
        return database.select("select amount from wallet w inner join users c on c.walletId=w.id where c.id= "+companyId);
    }
    public ResultSet findWalletByDistrictId(int district_id){
        return database.select("select amount from wallet w inner join users d on d.walletId=w.id where d.id="+district_id);
    }
    public ResultSet findWalletByUserId(int userid){
        return database.select("select amount from wallet w inner join users c on c.walletId=w.id where c.id"+userid);
    }
    public ResultSet findWalletByAdminId(int admins_id){
        return database.select("select amount from wallet w inner join users a on a.walletId=w.id where a.id = "+admins_id);
    }
    public ResultSet findWallet(int userId){
        return database.select("select amount from wallet w inner join users u on u.wallet=w.id where u.id = "+userId);
    }

    public ResultSet findWalletsCount(){
        return database.select("SELECT COUNT(*) AS totalWallets FROM wallet;");
    }

    public boolean addWallet(){
        return database.insert("INSERT INTO wallet (amount,status) VALUES (0,1) ");
    }
}