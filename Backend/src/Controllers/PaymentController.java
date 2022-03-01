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
