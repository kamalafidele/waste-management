package Components.House;

import java.util.Scanner;

public class Dashboard {
    Scanner keyboard = new Scanner(System.in);

    public void handleDashboard(){
            int choice = 0;
            System.out.println("\n");
            System.out.println("--------Dashboard--------");
            System.out.println("--------Please choose an option----------");
            System.out.println("1.Pay wastes");
            System.out.println("2.Pay security");
            System.out.println("3.Your invoices");
            System.out.println("4.Notifications&messages");
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();

            switch (choice){
                case 1:
                    System.out.println("wastes payment");
                    myMethod();
                    break;
                case 2:
                    System.out.println("security payment");
                    break;
                case 3:
                    System.out.println("invoices");
                    break;
                case 4:
                    System.out.println("Notifications&messages");
                    break;

                default:
                    System.out.println("Please be serious!");
                    break;
            }
    }
    public void myMethod(){
        System.out.println("my method");
    }
}

