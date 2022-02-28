package Components;

import javax.net.ssl.SSLContext;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.Scanner;

public class Payment {
    DataOutputStream outToServer;
    DataInputStream fromServer;
    Scanner keyboard=new Scanner(System.in);
    public void mainMethod(){
        System.out.println("hey there");
        System.out.println("please choose what to do here");
        System.out.println("1. check your debt");
        System.out.println("2. proceed with payment");
        int option=keyboard.nextInt();
        switch (option){
            case 1:
                checkDebt(outToServer,fromServer);
                break;
            case 2:
                //payment methods;
                break;
            default:
                System.out.println("please be serious");
        }
    }
    //a view where client can do his payment be it waste or security;
    public void checkDebt(DataOutputStream outToServer,DataInputStream fromServer){
        this.outToServer=outToServer;
        this.fromServer=fromServer;
        System.out.println("choose which type of debt you want to check");
        System.out.println("1. Wastes debt \t \t 2. Security Debt");
        int operation=keyboard.nextInt();
        try {
            String request;
            String response;
            switch (operation){
                case 1:
//                checkWasteDebt(outToServer,fromServer);
                    request="payment/checkSecurityDebt";
                    outToServer.writeUTF(request);
                    response=fromServer.readUTF();
                    System.out.println(response);
                    break;
                case 2:
//                    checkSecurityDebt(outToServer,fromServer);
                    request="payment/checkWasteDebt";
                    outToServer.writeUTF(request);
                    response=fromServer.readUTF();
                    System.out.println(response);
                    break;
                default:
                    System.out.println("please be serious");
            }
        }
        catch (IOException ie){}
    }
    public void checkSecurityDebt(DataOutputStream outToServer,DataInputStream fromServer){
        String request="payment/checkSecurityDebt";
        try {
            outToServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
    public void checkWasteDebt(DataOutputStream outToServer,DataInputStream fromServer){
        try {
            String request="payment/checkWasteDebt";
            outToServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }
}
