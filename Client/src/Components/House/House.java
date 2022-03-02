package Components.House;

import Components.House.Dashboard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class House {
//    private String fullnames;
//    private String nid;
//    private String houseno;
//    private String telno;
//    private String sector;
//    private String cell;
//    private String village;
//    private String token;
    DataOutputStream toServer;
    DataInputStream fromClient;
    Scanner keyboard = new Scanner(System.in);
//    citizen/insert/{ "fullnames" : "karera marvin", "nid" : "12345678", "telno" : "indmts22", "telno" : "250788124399", "sector" : "niboye","cell" : "lorem", "village" : "indamutsa" }

    public House(){};
    public House(DataInputStream fromClient, DataOutputStream toServer){
        this.fromClient=fromClient;
        this.toServer = toServer;
    };

    public void handleHouse(){
        System.out.print("\n");
        System.out.println("--------Login as an House!----------");
        System.out.print("Your token: ");
        String token = keyboard.next();

        //call login function
        boolean loggedIn = login(token);
        if(!loggedIn){
            System.out.println("Try again!");
        }else{
            //dashboard
            Dashboard dashboard = new Dashboard(fromClient,toServer);
            dashboard.handleDashboard();
        }
    }

    public boolean login(String token){
        //calling login api
        //<---------->
        return true;
    }
}
