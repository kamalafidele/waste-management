package Components;

import java.util.Scanner;

public class customerInvoice {
    public void mainMethod() throws Exception {
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t+             INVOICE MANAGEMENT            + ");
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t|| 1.  VIEW INVOICES               ||");
        System.out.println("\t\t\t|| 2.  DOWNLOAD INVOICE                 ||");
        System.out.println("\t\t\t---------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
       switch (choice){
           case 1 -> System.out.println("Call the function to the list of all invoices");
           case 2 ->System.out.println("Display the dates of the available invoices and ask them which one they want to download");
           default ->System.out.println("\t\t\t\t Invalid input");
       }
    }
}