package Components.House;

import Components.House.Dashboard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class House{
//    private String fullnames;
//    private String nid;
//    private String houseno;
//    private String telno;
//    private String sector;
//    private String cell;
//    private String village;
//    private String token;
    DataOutputStream toServer;
    Scanner keyboard = new Scanner(System.in);
//    citizen/insert/{ "fullnames" : "karera marvin", "nid" : "12345678", "houseno" : "indmts22", "telno" : "250788124399", "sector" : "niboye","cell" : "lorem", "village" : "indamutsa" }
//    citizen/getSingle/12349

    public House(DataOutputStream toServer){
        this.toServer = toServer;
    }

    public void handleHouse(DataInputStream fromServer, DataOutputStream toServer){
        System.out.print("\n");
        System.out.println("--------Login as an House!----------");
        System.out.print("Your pin: ");
        String token = keyboard.next();

        //call login function
        boolean loggedIn = login(token);
        if(!loggedIn){
            System.out.println("Try again!");
        }else{
            //dashboard
            Dashboard dashboard = new Dashboard();
            dashboard.handleDashboard(fromServer, toServer);
        }
    }

    public boolean login(String token){
        //calling login api
        //<---------->
        return true;
    }
}
