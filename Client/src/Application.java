
import Components.Admin.Admin;
import Components.Company;
import Components.House.House;
import Components.Wallet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import Components.customerInvoice;
import DataHandlers.CustomerInvoicesHandler;

public class Application {

    // just some colors
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args){
        Scanner keyboard=new Scanner(System.in);
        try{
            Socket socket=new Socket("localhost",3000);
            int choice = 0;

            DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
            DataInputStream fromServer=new DataInputStream(socket.getInputStream());


            System.out.println("--------------------------------------------------WELCOME TO----------------------------------------------          " + RESET);
            System.out.println(BLUE + "\\\\              //\\\\             //   ==================   ||\\\\             //||   ==================  " + RESET);
            System.out.println(BLUE + " \\\\            //  \\\\           //    ||                   || \\\\           // ||   ||                  " + RESET);
            System.out.println(BLUE + "  \\\\          //    \\\\         //     ||                   ||  \\\\         //  ||   ||                  " + RESET);
            System.out.println(BLUE + "   \\\\        //      \\\\       //      ==================   ||   \\\\       //   ||   ==================  " + RESET);
            System.out.println(BLUE + "    \\\\      //        \\\\     //                       ||   ||    \\\\     //    ||                   ||  " + RESET);
            System.out.println(BLUE + "     \\\\    //          \\\\   //                        ||   ||     \\\\   //     ||                   ||  " + RESET);
            System.out.println(BLUE + "      \\\\  //            \\\\ //         ==================   ||      \\\\ //      ||   ==================  " + RESET);
            System.out.println("\n\n");

            System.out.println("--------Please choose your role----------");
            System.out.println("1.Admin");
            System.out.println("2.District");
            System.out.println("3.Company");
            System.out.println("4.Confirmer");
            System.out.println("5.House");
            System.out.println("6.Invoices");
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();

            switch (choice){
                case 1:
                    Admin admin = new Admin(toServer,fromServer);
                    admin.handleAdmin();
                    break;
                case 2:
                    System.out.println("You are a district!");
                     new Company(toServer, fromServer).addCitizen();
                    break;
                case 3:
                    new Company(toServer, fromServer).displayCompanies();
                    System.out.println("You are a company!");
                    break;
                case 4:
                    System.out.println("You are a confirmer!");
                    break;
                case 5:
                    System.out.println("You are a citizen!");
                    House house = new House(toServer, fromServer);
                    house.handleHouse(fromServer, toServer);
                    break;
                case 6:
                    customerInvoice customer = new customerInvoice();
                    try {
                        customer.mainMethod();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
                
                default:
                    System.out.println("Please be serious!");
                    break;
            }


        }catch(IOException exception){
            System.out.println("here");
            exception.printStackTrace();
        }catch (InputMismatchException exception){
            System.out.println("Please be serious!");
        }
    }
}
