
import Components.*;
import Components.Admin.Admin;
import Components.House.House;
import Components.District.DistrictDashboard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import Components.Shifts;

public class Application {

    // just some colors
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args){
        Scanner keyboard=new Scanner(System.in);
        try{
            Socket socket=new Socket("localhost",2500);
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
                    DistrictDashboard districtDashboard=new DistrictDashboard(toServer,fromServer);
                    districtDashboard.handleDistrict();
                    break;
                case 3:
                    new Company(toServer, fromServer).displayCompanies();
                    System.out.println("You are a company!");
                    new Company(toServer,fromServer).addCompany();
                    break;
                case 4:
                    System.out.println("You are a confirmer!");
                    ServiceConfirmationProcess loginProcess = new ServiceConfirmationProcess(toServer,fromServer);
                    String loginRes = loginProcess.loginConfirmer();
                    if(Objects.equals(loginRes, "true")){
//                    new Shifts(toServer,fromServer).addShift();'
                        System.out.println("WHAT DO U WANNA DO");
                        System.out.println("1.CREATE SHIFT");
                        System.out.println("2.CONFIRM SERVICE");
                        int response = keyboard.nextInt();
                        if (response == 1) {
                            new Shifts(toServer, fromServer).addShift();
                        }
                        if (response == 2) {
                            new ServiceConfirmation(toServer, fromServer).addConfirmedService();
                        }
                    }else {
                        System.out.println("Invalid credentials");
                    }

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
