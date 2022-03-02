package Controllers;

import Repositories.PaymentRepo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentController {
    private PaymentRepo paymentRepo;

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
    public void bankPayment(int accNumber, int amount, String token) {
        //Checking if the amount > 1000
        System.out.println("The amount "+amount);
        System.out.println("The boolean result "+(amount > 1000));

        if (amount > 1000){
            sendResponse("The maximum amount is 1000");
            return;
        }


        // Sending response to the client
        ResultSet resultSet = paymentRepo.findBankAccount(accNumber);
        int accNber =0;
        int bankBalance = 0;
        try{
            while(resultSet.next()){
                accNber = resultSet.getInt("accNber");
                bankBalance = resultSet.getInt("balance");
            }

            // Checking if the amount provided by the user is not greater than the amount of money on momo acc.
            if (amount > bankBalance){
                sendResponse("You don't have sufficient money on your account !");
                return;
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        System.out.println(accNber);
        System.out.println(accNumber);


        if(accNber!=accNumber){
            sendResponse("The number you entered doesn't have momo account.Please make sure you entered the right number ");
            return;
        }
        paymentRepo.transferFunds(accNber, amount, token);
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
            case "bankpayment":
                int accNumber =Integer.parseInt(request.split("/")[2]);
                int bamount =Integer.parseInt(request.split("/")[3]);
                String btoken = request.split("/")[4];
                this.bankPayment(accNumber, bamount, btoken);
            case "checkSecurityDebt":
                checkSecurityDebt();
                break;
        }
    }

    public void checkSecurityDebt(){

    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

}
