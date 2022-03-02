package Controllers;

import Repositories.PaymentRepo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentController {

    private PaymentRepo paymentRepo;
    private ResultSet user;
    public PaymentController(){
        paymentRepo = new PaymentRepo();
    };

    private DataOutputStream toClient;
    public void momoPayment(String phoneNumber){
        // Sending response to the client
        ResultSet resultSet = paymentRepo.findMomoAccountByNumber(phoneNumber);
        String phoneNber = "0";
        try{
            while(resultSet.next()){
                phoneNber = resultSet.getString("phoneNber");
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        System.out.println(phoneNber);
        System.out.println(phoneNumber);


        if(!phoneNber.equals(phoneNumber)){
            sendResponse("The number you entered doesn't have momo account.Please make sure you entered the right number ");
            return;
        }




    };

    public void filterRequest( String request, DataOutputStream toClient ) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "momopayment":
                String phoneNumber = request.split("/")[2];
                this.momoPayment(phoneNumber);
                break;
            case "checkWasteDebt":
                long userId=Long.valueOf(request.split("/")[2]);
                checkWasteDebt(userId);
                break;
        }
    }

    public void checkWasteDebt(long userId) {
        long balance=0;
        try {
            ResultSet result=paymentRepo.getBalance(userId);
            while (result.next()){
                balance=result.getLong(3);
            }
            System.out.println(balance);
            if(balance>0){
                System.out.println("you dont have a debt");
                sendResponse("ooh wow you don't have any debt \t you have: "+balance+"Frw in wallet");
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
    public void checkSecurityDebt(long userId) {
        long balance=0;
        try {
            ResultSet result=paymentRepo.getBalance(userId);
            while (result.next()){
                balance=result.getLong(3);
            }
            System.out.println(balance);
            if(balance>0){
                System.out.println("you dont have a debt");
                sendResponse("ooh wow you don't have any debt \t you have: "+balance+"Frw in wallet");
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
    public boolean isDebtFree(int amount,long userId){
        int balance=0;
        try {
            ResultSet result=paymentRepo.getBalance(userId);
            while (result.next()){
                 balance=result.getInt(3);
            }
            if(balance>0){
                return true;
            }
            return false;
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
