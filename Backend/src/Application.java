import java.io.IOException;
import java.net.ServerSocket;

public class Application {
    public static void main(String[] args){

        try{
            var serverSocket=new ServerSocket(2500);
            System.out.println("########## SERVER RUNNING ON PORT 2500 ##############");

             while(3>2){
                 var socket=serverSocket.accept();
                 ThreadHandler threadHandler=new ThreadHandler(socket);
                 threadHandler.start();
             }

        }catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
