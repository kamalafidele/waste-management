import Config.DatabaseConnection;
import Models.ConfirmerProcess;
import java.sql.ResultSet;

public class ConfirmerProcessRepo {
    DatabaseConnection dbconn = new DatabaseConnection();

    public ConfirmerProcessRepo(){

    }
    public ConfirmerProcessRepo(Long id,String Village,String Company,String HouseCode){

    }
    public ResultSet findALl(){
        return this.dbconn.select("select * from ConfirmerProcess");
    }
    public ResultSet findById( int id){
        return this.dbconn.select("select * from ConfirmerProcess where id = " + id);
    }

    public void save(ConfirmerProcess c1){
        DatabaseConnection con1 = this.dbconn;
        con1.insert("INSERT INTO ConfirmerProcess(id,Village,Company,HouseCode) VALUES (" + c1.getId()+ "," + var1.getVillage() + "," + var1.getCompany() + "," + var1.getHouseCode() + ")");
    }
}