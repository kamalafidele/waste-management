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

    public void momoPayment(String phoneNumber, int amount, String token){
        //Checking if the amount > 1000
        System.out.println("The amount "+amount);
        System.out.println("The boolean result "+(amount > 1000));

        if (amount > 1000){
            sendResponse("The maximum amount is 1000");
            return;
        }


        // Sending response to the client
        ResultSet resultSet = paymentRepo.findMomoAccountByNumber(phoneNumber);
        String phoneNber = "0";
        int momoBalance = 0;
        try{
            while(resultSet.next()){
                phoneNber = resultSet.getString("phoneNber");
                momoBalance = resultSet.getInt("balance");
            }

            // Checking if the amount provided by the user is not greater than the amount of money on momo acc.
            if (amount > momoBalance){
                sendResponse("You don't have sufficient money on your account !");
                return;
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
        paymentRepo.transferMoney(phoneNber, amount, token);
        sendResponse("Your payment has been recorded!");


    };

    public void filterRequest( String request, DataOutputStream toClient ) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "momopayment":
                String phoneNumber = request.split("/")[2];
                int amount =Integer.parseInt(request.split("/")[3]);
                String token = request.split("/")[4];
                this.momoPayment(phoneNumber, amount, token);
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
                System.out.println("you don't have a debt");
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
