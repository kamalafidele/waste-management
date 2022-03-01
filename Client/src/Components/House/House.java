package Components.House;

import java.io.DataOutputStream;
import java.util.Scanner;

public class House extends Dashboard {
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
//    citizen/insert/{ "fullnames" : "karera marvin", "nid" : "12345678", "telno" : "indmts22", "telno" : "250788124399", "sector" : "niboye","cell" : "lorem", "village" : "indamutsa" }
//    citizen/getSingle/12349

    public House(DataOutputStream toServer){
        this.toServer = toServer;
    }

    public void handleHouse(){
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
            dashboard.handleDashboard();
        }
    }

    public boolean login(String token){
        //calling login api
        //<---------->
        return true;
    }
}
