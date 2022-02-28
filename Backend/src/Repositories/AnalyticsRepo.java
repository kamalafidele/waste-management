package Repositories;

import Config.DatabaseConnection;
public class AnalyticsRepo {
    DatabaseConnection database;
    public AnalyticsRepo(){
        database = new DatabaseConnection();
    }

    public String districtAnalytics(String period){
//        select analytics for district in that period
        return "";
    }

    public String companyAnalytics(String period){
        String analytics = "Number of service provided by us:  2\nTotal amount of money so far : 20000frw\n" +
                "Percentage at which service is provided: 80%\n========END========";
//        select analytics for company in the specified period
        return analytics;
    }
}
