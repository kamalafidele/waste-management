package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Payment {
    private Integer amount;
    Scanner scanner = new Scanner(System.in);
    DataOutputStream toServer;
    DataInputStream fromServer;

    public Payment(DataInputStream fromServer, DataOutputStream toServer){
        this.toServer = toServer;
        this.fromServer = fromServer;
    }
    public void checkDebt(){
        System.out.println("want to check which debt");
        System.out.println("1. security Debt \t \t \t 2. Waste debt");
        int option=scanner.nextInt();
        switch (option){
            case 1:
                checkSecurityDebt(toServer,fromServer);
                break;
            case 2:
                checkWasteDebt(toServer,fromServer);
                break;
            default:
                System.out.println("please be serious");
        }
    }
    public void checkSecurityDebt(DataOutputStream toServer,DataInputStream fromServer){
        this.toServer=toServer;
        this.fromServer=fromServer;
        String request="payment/checkSecurityDebt";
        try {
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            System.out.println("An error occured");
        }
    }
    public void checkWasteDebt(DataOutputStream toServer,DataInputStream fromServer){
        this.toServer=toServer;
        this.fromServer=fromServer;
        String request="payment/checkWasteDebt";
        try {
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            System.out.println("An error occured");
        }
    }
    public void handleMomopayment(){
        System.out.print("Telephone: ");
        String telephoneNumber = scanner.next();
        System.out.print("Amount: ");
        int amount = scanner.nextInt();

        // Formulating a request and making a request
        String request = "payment/momopayment/" + telephoneNumber +"/"+amount;
        try{
            this.toServer.writeUTF(request);
            String responseFromServer = fromServer.readUTF();
            System.out.println("Hello world !");

            System.out.println("Response from the server: " + responseFromServer);

        }catch(Exception ex){
            ex.printStackTrace();
        }


    };
    public void handleBankpayment(){
        System.out.println("I am a handleMomopayment");
    };

    public void handlePaymentMethods(){
        System.out.println("--------Please choose payment method----------");
        System.out.println("1. Pay by momo");
        System.out.println("2. Pay by bank");

        int paymentMethod;
        System.out.print("Your choice: ");
        paymentMethod = scanner.nextInt();

        switch(paymentMethod){
            case 1:
                this.handleMomopayment();
                break;
            case 2:
                this.handleBankpayment();
                break;
            default:
                System.out.println("Please enter a valid option");
                break;
        }

    }


}
