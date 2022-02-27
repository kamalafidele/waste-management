package Components;

import java.util.Scanner;

public class Admin {
    Scanner keyboard = new Scanner(System.in);
    public boolean login(){
        //Getting login information
        System.out.print("\n");
        System.out.println("--------Login as an admin!----------");
        System.out.print("Username: ");
        String username = keyboard.next();
        System.out.print("Password: ");
        String password = keyboard.next();

        //calling login api


        return true;
    }
}
