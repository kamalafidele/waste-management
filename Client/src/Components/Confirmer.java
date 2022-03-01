package Components;

import java.util.Scanner;
public class Confirmer {
     private String date;
     private String village;
     private String companyName;
     private String houseCode;
    Scanner scanner =new Scanner(System.in);

    public boolean login(){
        //Getting login information
        System.out.print("\n");
        System.out.println("--------Confirmer login----------");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        //calling login api
        return true;
    }

    public void confirmerUI(){
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t+           Confirm Garbage Collection          + ");
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t---------------------------------------------");

        System.out.println("Enter Date of garbage collection");
        date =scanner.nextLine();
        System.out.println("Enter Area of collection");
        village =scanner.nextLine();
        System.out.println("Enter House code");
        houseCode =scanner.nextLine();
        System.out.println("Enter Company working in " + village + " village");
        companyName =scanner.nextLine();

    /* Send data For validation and return response*/
        System.out.println("Confirmed that garbage from " + houseCode + " has been successfully collected");
    }

}
