package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Debt {
     DataOutputStream toServer;
     DataInputStream fromServer;
     Scanner scanner=new Scanner(System.in);
     public Debt(DataInputStream fromServer,DataOutputStream toServer){
         this.toServer=toServer;
         this.fromServer=fromServer;
     }
     public void checkBalance(){
         long pin=12345;
         String request="debt/checkBalance/"+pin;
         try {
             toServer.writeUTF(request);
             System.out.println(fromServer.readUTF());
         }
         catch (IOException ie){
             ie.printStackTrace();
         }
     }
     public void checkDebt(){
         System.out.println("want to check which type of debt");
         System.out.println("1. Security Debt \t \t 2. waste debt");
         int option=scanner.nextInt();
         switch (option){
             case 1:
                 checkSecurityDebt();
                 break;
             case 2:
                 System.out.println("waste debt");
                 checkWasteDebt();
                 break;
             default:
                 System.out.println("please be serious");
         }
     }
    public void checkSecurityDebt(){
        long userId=12345;
        String request="debt/checkSecurityDebt/"+userId;
        try {
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
            System.out.println("An error occured");
        }
    }
    public void checkWasteDebt(){
        long userId=12345;
        String request="debt/checkWasteDebt/"+userId;
        try {
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
            System.out.println("An error occured");
        }
    }
}
