/*
 * This is the WASM system entry point
 * You're welcome!
 * @author: Turinumugisha Souvenir
 * */

import Models.RequestBody;

import java.util.Scanner;

public class Main {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void welcomePage() throws Exception {
        System.out.println("\n\n\n                                                                                                       ");
        System.out.println("\t\t\t\t                                                                                                       ");
        System.out.println("\n\n\n                                                                                                       ");
        System.out.println("\t\t\t\t                                                                                                       ");
        System.out.println(ANSI_BLUE + "\t\t\t\t                                                                                         " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t    --------                                    --------                                 " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t     \\      \\                                  /      /                                      " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t      \\      \\                                /      /                       " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t       \\      \\           ------             /      /          " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t        \\      \\         /      \\           /      /                     " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t         \\      \\       /        \\         /      /                    " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t          \\      \\     /          \\       /      /              " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t           \\      \\   /      /\\    \\     /      /       " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t            \\      \\ /      /  \\    \\   /      /     " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t             \\             /    \\             /               " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t              \\           /      \\           /                     " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t               \\         /        \\         /                 " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t                \\       /          \\       /                 " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t                 -------            -------                     " + ANSI_RESET);

    }
    public static void welcomeToWASM() {
        RequestBody requestBody = new RequestBody();
       String toContinue;
        do {
            int choice;
            Scanner scanner= new Scanner(System.in);
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      WELCOME TO WASM        ------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");

            /*Here is where we have to check if the user is logged in or not
            * DISPLAY THE USER OPTIONS HERE TOO
            * */
            System.out.println("\t\t\t||------------------    1.CHOICE ONE                ------------------||");
            System.out.println("\t\t\t||------------------    2.CHOICE TWO       ------------------||");
            System.out.println("\t\t\t||------------------    3.CHOICE THREE            ------------------||");
            System.out.println("\t\t\t||------------------    4.CHOICE FOUR                   ------------------||");
            System.out.println("\t\t\t||------------------    5.CHOICE FIVE                   ------------------||");
            System.out.println("\t\t\t||------------------    6.CHOICE SIX            ------------------||");

            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice                                              ");
            choice = scanner.nextInt();
            toContinue= scanner.next();
        System.out.print("\t\tDo you want to continue? (y/n): ");
        toContinue = scanner.next();
        switch (choice){
            // Here is where to treat all the case according to the users choice
        }
    }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }

    public static void main(String[] args) throws Exception {
        welcomeToWASM();
    }
}
