package Repositories;

import Config.DatabaseConnection;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnalyticsRepo {
    DatabaseConnection database;
    public AnalyticsRepo(DatabaseConnection database){
        this.database = database;
    }

    public String districtAnalytics(String period){
//        select analytics for district in that period
        return "";
    }

    public ResultSet companyAnalytics(String period, Integer senderId){
        return database.select("select name, balance from company c join " +
                "companies_wallets cw on c.id = cw.company_id where id = " + senderId);
    }

}
