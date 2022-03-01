package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
