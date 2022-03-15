package Controllers;

import Repositories.DebtRepo;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtController {
    private DebtRepo debtRepo;
    private DataOutputStream toClient;
    public long balance;
    public DebtController(){
        debtRepo=new DebtRepo();
    };
    public void filterRequest(String request, DataOutputStream toClient){
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "checkWasteDebt":
                System.out.println("hey there");
                String userId=String.valueOf(request.split("/")[2]);
                checkWasteDebt(userId);
                break;
        }
    }
    public void checkWasteDebt(String userId){
        try {
            ResultSet result=debtRepo.getBalance(userId);
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
            System.out.println(userId);
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
                this.balance=result.getLong(3);
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
