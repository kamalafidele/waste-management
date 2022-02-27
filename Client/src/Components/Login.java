package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Login {
    Scanner keyboard=new Scanner(System.in);
    DataOutputStream toServer;
    DataInputStream fromServer;

    //ALL NEEDED COMPONENTS WILL BE REGISTERED HERE
    /* EXAMPLE: Admin admin = new Admin(); */

    public Login(){}

    public void handleLogin(DataOutputStream toServer, DataInputStream fromServer){
        this.toServer=toServer;
        this.fromServer=fromServer;
        int choice=0;

        do {
            System.out.println("############## WELCOME TO WS APP     #################");
            System.out.println("############## USE AVAILABLE OPTIONS #################");
            System.out.println("1. LOGIN AS ADMIN ");
            System.out.println("2. LOGIN AS COMPANY ");
            System.out.println("3. LOGIN AS CITIZEN ");
            System.out.println("0. EXIT ");
            System.out.print("CHOOSE AN OPTION : ");
            choice=keyboard.nextInt();

            switch (choice){
                case 1 :
                    adminLogin();
                  break;
                case 2 :
                    companyLogin();
                  break;
                case 3 :
                    citizenLogin();
                  break;
                case 0 :
                    System.exit(1);
                  break;
                default:
                    System.out.println("_____ PLEASE CHOOSE A VALID OPTION ______");
                  break;
            }
        } while (choice != 0);

    }

    public void adminLogin(){
        System.out.println("ADD YOUR LOGIC IN THIS METHOD ");
    }

    public void companyLogin(){
        System.out.println("ADD YOUR LOGIC IN THIS METHOD ");
    }

    public void citizenLogin(){
        System.out.println("ADD YOUR LOGIC IN THIS METHOD ");
    }

}
