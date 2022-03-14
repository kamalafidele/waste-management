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
     public void checkDebt(){
         System.out.println("Check your debt");
         System.out.println("want to check which debt");
         System.out.println("1. Security Debt \t \t 2. waste debt");
         int option=scanner.nextInt();
         switch (option){
             case 1:
                 checkSecurityDebt();
                 break;
             case 2:
                 checkWasteDebt();
                 break;
             default:
                 System.out.println("please be serious");
         }
     }
    public void checkSecurityDebt(){
        long userId=1;
        String request="debt/checkSecurityDebt/"+userId;
        try {
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
            System.out.println("An error occurred");
        }
    }
    public void checkWasteDebt(){
        long userId=1;
        String request="debt/checkWasteDebt/"+userId;
        try {
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
            System.out.println("An error occurred");
        }
    }
}
