package Components;
import DataHandlers.ConfirmerHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class Confirmer {
    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner keyboard;
    ObjectMapper mapper;
    public Confirmer(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer=toServer;
        this.fromServer=fromServer;
        keyboard=new Scanner(System.in);
        mapper=new ObjectMapper();
    }

    public void addConfirmer() {

        var confirmerHandler=new ConfirmerHandler();
        System.out.println( "######## ADDING NEW CONFIRMER #########" );
        System.out.print( "Enter  username: " );
        confirmerHandler.setuserName( keyboard.nextLine() );
        System.out.print( "Enter confirmer email: " );
        confirmerHandler.setEmail( keyboard.nextLine() );
        System.out.print( "Enter confirmer phone: " );
        confirmerHandler.setPhone( keyboard.nextLine() );
        System.out.print( "Enter password: " );
        confirmerHandler.setPassword(Integer.parseInt(keyboard.nextLine()));
        confirmerHandler.setRole( 5 );

        try{
            String confirmerAsJson=mapper.writeValueAsString( confirmerHandler );
            sendRequest( "confirmer/addConfirmer/" + confirmerAsJson );
            String response= fromServer.readUTF();
            System.out.println( response );
        }catch (IOException exception){}
    }

    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){}
    }


}
