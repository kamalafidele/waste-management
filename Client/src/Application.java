import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Application {

    // just some colors
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args){
        Scanner keyboard=new Scanner(System.in);
        try{
            Socket socket=new Socket("localhost",2500);

            DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
            DataInputStream  fromServer=new DataInputStream(socket.getInputStream());


            System.out.println("");
            System.out.println(BLUE + "\t\t\t    \\\\              //\\\\             //   ==================   ||\\\\             //||   ================== ");
            System.out.println(BLUE + "\t\t\t     \\\\            //  \\\\           //    ||                   || \\\\           // ||   ||");
            System.out.println(BLUE + "\t\t\t      \\\\          //    \\\\         //     ||                   ||  \\\\         //  ||   ||");
            System.out.println(BLUE + "\t\t\t       \\\\        //      \\\\       //      ==================   ||   \\\\       //   ||   ================== ");
            System.out.println(BLUE + "\t\t\t        \\\\      //        \\\\     //                       ||   ||    \\\\     //    ||                   || ");
            System.out.println(BLUE + "\t\t\t         \\\\    //          \\\\   //                        ||   ||     \\\\   //     ||                   ||");
            System.out.println(BLUE + "\t\t\t          \\\\  //            \\\\ //         ==================   ||      \\\\ //      ||   ================== ");

            String response=fromServer.readUTF();
            System.out.println(response);

        }catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
