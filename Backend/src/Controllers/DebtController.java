package Controllers;

import Config.DatabaseConnection;
import Repositories.DebtRepo;

import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtController {
    private DebtRepo debtRepo;
    private DataOutputStream toClient;
    private NotificationController notificationController=new NotificationController();
    public long balance;
    private DatabaseConnection connection;

    public DebtController(DatabaseConnection connection){
        this.connection = connection;
        debtRepo=new DebtRepo(connection);
    };
    public void filterRequest(String request, DataOutputStream toClient){
        this.toClient=toClient;
        String pin=String.valueOf(request.split("/")[3]);
        String service=String.valueOf(request.split("/")[2]);
        switch (request.split("/")[1]) {
            case "checkWasteDebt":
                System.out.println("waste debt");
                checkWasteDebt(pin);
                break;
            case "checkSecurityDebt":
                checkSecurityDebt(pin);
                break;
            case "checkBalance":
                checkBalance(pin);
        }
    }

    public void checkBalance(String pin){
        try {
            String response;
            ResultSet resultSet=debtRepo.getBalance(pin);
            long balance=resultSet.getLong(4);
            System.out.println(balance);
            sendResponse("Your balance: "+balance);
        }
        catch (SQLException ie){
            ie.printStackTrace();
        }
    }
    public void checkSecurityDebt(String userId){
        try {
//            ResultSet result=debtRepo.getBalance(userId);
            ResultSet result=debtRepo.getMySecurityDebt(userId);
            if(result==null){
                sendResponse("You don't have a debt for security service");
            }
            balance=result.getLong(1);
            Date date=result.getDate(2);
            System.out.println(balance);
            String[] months ={"january","February","March","April","May","June","July","August","september","October","November","December"};
            String month="January";
            for (int i=0;i<months.length;i++){
                month=months[Integer.parseInt(date.toString().split("-")[1])-1];
            }
            if(balance>0){
                notificationController.createNotification(1,"paymentWarningNotification");
                sendResponse("OOPs you have a debt of"+balance+"Frw accumulated in "+month);
                return;
            }
            if(balance>3000){
                sendResponse("you have a maximum debt of: "+balance+"Frw in wallet");
                return;
            }
            if(balance==0){
                sendResponse("You don't have any debt");
                return;
            }
        }
        catch (SQLException sql){
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
    }
    public void checkWasteDebt(String userId){
        try {
//            ResultSet result=debtRepo.getBalance(userId);
            ResultSet result=debtRepo.getMyWasteDebt(userId);
            if(result==null){
                sendResponse("An error occurred");
            }
            balance=result.getLong(1);
            Date date=result.getDate(2);
            String[] months ={"january","February","March","April","May","June","July","August","september","October","November","December"};
            String month="January";
            for (int i=0;i<months.length;i++){
                month=months[Integer.parseInt(date.toString().split("-")[1])-1];
            }
            if(balance>0){
                notificationController.createNotification(1,"paymentWarningNotification");
                sendResponse("OOPs you have a debt of"+balance+"Frw accumulated in "+month);
                return;
            }
            if(balance>3000){
                sendResponse("you have a maximum debt of: "+balance+"Frw in wallet");
                return;
            }
            if(balance<0){
                sendResponse("you have a debt of: "+balance+"Frw in wallet");
                return;
            }
        }
        catch (SQLException sql){
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
    }
    public boolean isDebtLimit(String userId){
        ResultSet result=debtRepo.getBalance(userId);
        try {
            while(result.next()){
                this.balance=result.getLong(4);
            }
            if(this.balance<=-3000){
                sendResponse("OOPS you have reached maximum debt");
                return false;
            }
            return true;
        }
        catch (SQLException sql){
            sql.printStackTrace();
            return false;
        }
    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
