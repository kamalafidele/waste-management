import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

public class Application {
    public static void main(String[] args){
    // THIS THE ENTRY FOR OUR SERVER APPLICATION
        System.out.println("Started backend");
        try{
            ServerSocket serverSocket=new ServerSocket(2500);
            System.out.println("########## SERVER RUNNING ON PORT 2500 ##############");

             while(3>2){
                 var socket=serverSocket.accept();
                 ThreadHandler threadHandler=new ThreadHandler(socket);

                 // THIS IS TO CREATE A THREAD FOR EACH NEW CONNECTION TO OUR SERVER
                 threadHandler.start();
             }

        }catch(IOException exception){
            //exception.printStackTrace();
        }
    }
}
