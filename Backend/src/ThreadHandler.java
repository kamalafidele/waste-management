import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{
    Socket socket;

    public ThreadHandler(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run(){
        try{
            System.out.println("Client connected");
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());

            PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
            writer.println("Hello our client");
            //socket.close();

        }catch(IOException exception){}
    }
}
