package Controllers;

import Repositories.AnalyticsRepo;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnalyticsController {
    private DataOutputStream toClient;
    private AnalyticsRepo analyticsRepo;

    public AnalyticsController(){
        analyticsRepo = new AnalyticsRepo();
    }

    public void filterRequest(String request, DataOutputStream toClient) throws IOException {
        this.toClient=toClient;
        String sender = request.split("/")[0];
        Integer senderId;
        switch (request.split("/")[2]) {
            case "getWeekly":
                senderId = Integer.parseInt(request.split("/")[3]);
                getAnalytics(sender, senderId,"week");
                break;
            case "getMonthly":
                senderId = Integer.parseInt(request.split("/")[3]);
                getAnalytics(sender, senderId, "month");
                break;
            case "getAnnual":
                senderId = Integer.parseInt(request.split("/")[3]);
                getAnalytics(sender, senderId, "year");
                break;
            case "download":
                senderId = Integer.parseInt(request.split("/")[3]);
                downloadAnalytics(sender, senderId, request.split("/")[4]);
                break;
            default:
                sendResponse("Please specify your request....");
                break;
        }
    }

    public String getAnalytics(String sender, Integer senderId, String period){
        if(sender.equals("district")){
//            Logic for district analytics in the given period
            sendResponse("District analytics");
        }

        if(sender.equals("company")){
            ResultSet companyAnalytics = analyticsRepo.companyAnalytics(period, senderId);
            String companyName = null;
            Integer balance = 0;
            try {
                while (companyAnalytics.next()){
                    companyName = companyAnalytics.getString(1);
                    balance = Integer.parseInt(companyAnalytics.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String heading = "\n========"+ period.toUpperCase() + "LY " +"ANALYTICS========\n\n";
            String closing = "Done on: " + dtf.format(LocalDateTime.now()) + "\n" +  "========== END ==========\n";

            String body = "Company name: " + companyName + "\n" + "Number of service provided by us:  1\nTotal amount " +
                    "of money so far : " + balance + "\n" + "Percentage at which service is provided: 80%\n";

            sendResponse(heading + body + "\n" + closing);
            return heading + body + "\n" + closing;
        }
        return "";
    }

    public void downloadAnalytics(String sender, Integer senderId, String period) throws IOException {
        String analytics = getAnalytics(sender, senderId, period);
//        System.out.println(analytics);
        saveIntoFIle(analytics);
        sendResponse("Downloaded Successfully.");
    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
    public static void saveIntoFIle(String content){
//        The logic to save a .txt file containing analytics
        try{
            writeToFile(content, "Analytics.txt");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void writeToFile(String content, String path){

        try{
            File file= new File(path);
            BufferedWriter bw= new BufferedWriter(new FileWriter(path, false));
            bw.write(content);
            bw.close();
            System.out.println("Download Successfully.");


        }catch (Exception e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
