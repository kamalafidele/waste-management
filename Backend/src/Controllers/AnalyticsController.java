package Controllers;

import Repositories.AnalyticsRepo;

import java.io.DataOutputStream;
import java.io.IOException;

public class AnalyticsController {
    private DataOutputStream toClient;
    private AnalyticsRepo analyticsRepo;

    public AnalyticsController(){
        analyticsRepo = new AnalyticsRepo();
    }

    public void filterRequest(String request, DataOutputStream toClient){
        this.toClient=toClient;
//        Whose analytics are we retrieving company or district.
        String sender = request.split("/")[0];
        switch (request.split("/")[2]) {
            case "getWeekly":
                getWeeklyAnalytics(sender);
                break;
            case "getMonthly":
                getMonthlyAnalytics();
                break;
            case "getAnnual":
                getAnnualAnalytics();
                break;
            case "download":
                downloadAnalytics();
                break;
            default:
                sendResponse("Please specify your request....");
                break;
        }
    }

    public void getWeeklyAnalytics(String sender){
//        System.out.println("Weekly analytics");
        sendResponse("Weekly analytics");
    }
    public void getMonthlyAnalytics(){
        System.out.println("Monthly analytics");
    }
    public void getAnnualAnalytics(){
        System.out.println("Annual analytics");
    }
    public void downloadAnalytics(){
        System.out.println("Analytics downloaded");
    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
