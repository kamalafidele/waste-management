package Controllers;

import Repositories.AnalyticsRepo;

import java.io.DataOutputStream;
import java.io.IOException;
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

    public void filterRequest(String request, DataOutputStream toClient){
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
                downloadAnalytics(request.split("/")[3]);
                break;
            default:
                sendResponse("Please specify your request....");
                break;
        }
    }

    public String getAnalytics(String sender, Integer senderId, String period){
        if(sender.equals("district")){
            ResultSet nameBalance = analyticsRepo.getNameBalance(period, senderId);
            String districtName = null;
            Integer balance = 0;
            Integer totalDebt = 0;
            Integer companiesNum = 0;
            try {
                while (nameBalance.next()){
                    districtName = nameBalance.getString(1);
                    balance = Integer.parseInt(nameBalance.getString(2));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResultSet getDebt = analyticsRepo.getTotalDebts(senderId);
            try{
                while(getDebt.next()){
                    totalDebt = Integer.parseInt(getDebt.getString(1)) * -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultSet getCompaniesNum = analyticsRepo.companiesNumber(period, senderId);
            try{
                while (getCompaniesNum.next()){
                    companiesNum = Integer.parseInt(getCompaniesNum.getString(1));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String heading = "\n========"+ period.toUpperCase() + "LY " +"ANALYTICS========\n\n";
            String closing = "Done on: " + dtf.format(LocalDateTime.now()) + "\n" +  "========== END ==========\n";

            String body = "District name: " + districtName + "\n" + "Services being provided:  1\nTotal amount " +
                    "of money so far : " + balance + "\nMoney not paid yet: " + totalDebt + "\n" + "Number of employed" +
                    " companies: " + companiesNum + "\n";
            sendResponse(heading + body + "\n" + closing);
            return (heading + body + "\n" + closing);
        }

        if(sender.equals("company")){
            ResultSet companyAnalytics = analyticsRepo.getNameBalance(period, senderId);
            String companyName = null;
            Integer balance = 0;
            try {
                while (companyAnalytics.next()){
                    companyName = companyAnalytics.getString(1);
                    balance = Integer.parseInt(companyAnalytics.getString(2));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String heading = "\n========"+ period.toUpperCase() + "LY " +"ANALYTICS========\n\n";
            String closing = "Done on: " + dtf.format(LocalDateTime.now()) + "\n" +  "========== END ==========\n";

            String body = "Company name: " + companyName + "\n" + "Number of service provided by us:  1\nTotal amount " +
                    "of money so far : " + balance + "\n" + "Percentage at which service is provided: 80%\n";
            sendResponse(heading + body + "\n" + closing);
            return (heading + body + "\n" + closing);
        }
        return "Something went wrong.";
    }

    public void downloadAnalytics(String period){
        System.out.println("Analytics downloaded");
        saveIntoFIle("What to be saved");
    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
    public void saveIntoFIle(String content){
//        The logic to save a .txt file containing analytics
    }
}
