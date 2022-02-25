import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        Scanner keyboard=new Scanner(System.in);
        try{
            Socket socket=new Socket("localhost",2500);

            DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
            DataInputStream  fromServer=new DataInputStream(socket.getInputStream());

            System.out.print("Enter something: ");
            String request=keyboard.nextLine();

            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);

        }catch(IOException exception){

        }
    }
}
