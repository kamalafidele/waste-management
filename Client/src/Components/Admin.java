package Components;

import java.io.DataOutputStream;
import java.util.Scanner;

public class Admin {
    private String username;
    private String password;
    DataOutputStream toServer;
    Scanner keyboard = new Scanner(System.in);

    public Admin(){};
    public Admin(DataOutputStream toServer){
        this.toServer = toServer;
    };

    public void handleAdmin(){
        //get inputs from user
        System.out.print("\n");
        System.out.println("--------Login as an admin!----------");
        System.out.print("Username: ");
        String username = keyboard.next();
        System.out.print("Password: ");
        String password = keyboard.next();

        //call login function
        boolean loggedIn = login(username, password);
        if(!loggedIn){
            System.out.println("Try again!");
            //<-------------------->
        }else{
            //<---------------------->
            System.out.println("Lets goo");
        }
    }

    public boolean login(String username, String password){
        //calling login api
        //<---------->
        return true;
    }
}
