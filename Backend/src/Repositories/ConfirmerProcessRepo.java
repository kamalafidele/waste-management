package Repositories;
import Config.DatabaseConnection;
import Models.ConfirmerProcess;
import java.sql.ResultSet;

public class ConfirmerProcessRepo {
    DatabaseConnection dbconn = new DatabaseConnection();

    public ConfirmerProcessRepo(){

    }
    public ConfirmerProcessRepo(Long id,String Village,String Company,String HouseCode){

    }
    public ResultSet findAll(){
        return this.dbconn.select("select * from ConfirmerProcess");
    }
    public ResultSet findByHouseCode(String HouseCode){
        return this.dbconn.select("select * from ConfirmerProcess where HouseCode = " + HouseCode);
    }

    public void save(ConfirmerProcess c1){
        DatabaseConnection con1 = this.dbconn;
        con1.insert("INSERT INTO ConfirmerProcess(id,Village,Company,HouseCode) VALUES (" + c1.getId()+ "','" + c1.getVillage() + "','" + c1.getCompany() + "','" + c1.getHouseCode() + ")");
    }
}