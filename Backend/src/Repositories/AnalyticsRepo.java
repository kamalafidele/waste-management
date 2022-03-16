package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnalyticsRepo {
    DatabaseConnection database;
    public AnalyticsRepo(){
        database = new DatabaseConnection();
    }

    public String districtAnalytics(String period){
//        select analytics for district in that period
        return "";
    }

    public ResultSet getNameBalance(String period, Integer senderId){
        return database.select("select name, amount from users u join " +
                "wallet w on u.Wallet = w.id where u.id = " + senderId);
    }
    public ResultSet companiesNumber(String period, Integer senderId){
        return database.select(" select count(*) from district_company where DistrictId = " + senderId);
    }
    public ResultSet getTotalDebts(Integer senderId){
        return database.select("select sum(amount) as total_debt from wallet w join " +
                "users u on w.id = u.Wallet where u.role = 4");
    }
}
