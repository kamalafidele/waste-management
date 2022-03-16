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

    public ResultSet companyAnalytics(String period, Integer senderId){
        return database.select("select name, amount from users u join " +
                "wallet w on u.Wallet = w.id where u.id = " + senderId);
    }

}
