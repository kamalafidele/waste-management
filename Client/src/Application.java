import Components.Login;

import java.io.*;
import java.net.Socket;


public class Application {
    // APPLICATION SHOULD  CONTAIN LOGIN COMPONENT ONLY
    Login login;

    public Application(){
        login=new Login();
    }

    public static void main(String[] args){

        // THIS IS TO CREATE AN INSTANCE OF OUR APPLICATION IN ORDER TO ACCESS ITS PROPERTIES AND METHODS
        // WE HAVE TO CREATE IT INSIDE THE MAIN METHOD SINCE OUR MAIN IS A STATIC METHOD
        Application application=new Application();

        try{
            Socket socket=new Socket("localhost",2500);

            DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
            DataInputStream  fromServer=new DataInputStream(socket.getInputStream());

            application.login.handleLogin(toServer,fromServer);

        }catch(IOException exception){

        }
    }
}