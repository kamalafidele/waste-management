//package Components.House;
//
//import Components.Company;
//import Components.Debt;
//import Components.Notification;
//import Components.Payment;
//import DataHandlers.HouseHandler;
//import org.codehaus.jackson.map.ObjectMapper;
//
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Dashboard {
//    public static final String BLUE = "\033[0;34m";
//    public static final String RESET = "\033[0m";
//    DataOutputStream toServer;
//    DataInputStream fromServer;
//    ObjectMapper mapper;
//    Scanner keyboard = new Scanner(System.in);
//
//    public Dashboard() {
//    }
//
//    public Dashboard(DataOutputStream toServer, DataInputStream fromServer) {
//        this.toServer = toServer;
//        this.fromServer = fromServer;
//        ObjectMapper mapper;
//    }
//
//    public void handleDashboard(DataInputStream fromServer, DataOutputStream toServer, HouseHandler handler){
//        Payment payment = new Payment(fromServer, toServer);
//        Debt debt=new Debt(fromServer,toServer);
//        int choice = 0;
//        System.out.println("\n");
//        System.out.println("--------Dashboard--------");
//        System.out.println("--------Please choose an option----------");
//        System.out.println("1.Pay wastes");
//        System.out.println("2.Pay security");
//        System.out.println("3.Your invoices");
//        System.out.println("4.Notifications&messages");
//        System.out.println("5. check your debt");
//        System.out.println("6. view profile");
//        System.out.print("Your choice: ");
//        choice = keyboard.nextInt();
//
//        switch (choice){
//            case 1:
//            case 2:
//                payment.handlePaymentMethods();
//                break;
//            case 3:
//                System.out.println("invoices");
//                break;
//            case 4:
//                System.out.println("Notifications & Messages");
//                new Notification().displayAllNotifications(toServer, fromServer);
//                break;
//            case 5:
//                debt.checkDebt();
//                break;
//            case 6:
//                viewProfile(handler);
//                break;
//            default:
//                System.out.println("Please be serious!");
//                break;
//        }
//    }
//
//    public void viewProfile(HouseHandler handler) {
//        System.out.println("######################### YOUR INFORMATION (CITIZEN)###################################### ");
//        System.out.println( BLUE + " Name: " + RESET + handler.getName());
//        System.out.println( BLUE + " Email: " + RESET + handler.getEmail());
//        System.out.println( BLUE + " Phone: " + RESET + handler.getPhone());
//    }
//}
