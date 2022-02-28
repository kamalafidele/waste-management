package Components.House;

import Components.House.Dashboard;

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
    Scanner keyboard = new Scanner(System.in);

    public House(){};
    public House(DataOutputStream toServer){
        this.toServer = toServer;
    };

    public void handleHouse(){
        System.out.print("\n");
        System.out.println("--------Login as an House!----------");
        System.out.print("Your token: ");
        String token = keyboard.nextLine();

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
