package Components.House;

//import DataHandlers.CitizenHandler;
import DataHandlers.HouseHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInput;
import Components.House.Dashboard;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class House{

    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner keyboard = new Scanner(System.in);

    ObjectMapper mapper;
//    citizen/insert/{ "name" : "karera marvin", "email" : "karera@gmail.com", "phone" : "0781234567", "role" : 5, "location" : 2}
//    citizen/getSingle/12349

    public House(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
        mapper = new ObjectMapper();
    }

    public void handleHouse(DataInputStream fromServer, DataOutputStream toServer){
        System.out.print("\n");
        System.out.println("--------Login as a house owner!----------");
        System.out.print("Your pin: ");
        String token = keyboard.nextLine();

        //call login function
        login(token);
    }

    public void addCitizen(){
//        var citizenHandler=new CitizenHandler();
//        System.out.print( "Enter names: " );
//        citizenHandler.setName( keyboard.nextLine() );
//        System.out.print( "Enter email: " );
//        citizenHandler.setEmail( keyboard.nextLine() );
//        System.out.print( "Enter phone: " );
//        citizenHandler.setPhone( keyboard.nextLine() );
//        System.out.print( "Enter location: " );
//        citizenHandler.setLocation( keyboard.nextLine() );
//        citizenHandler.setRole(5);

        System.out.println("request: ");
        String request = keyboard.nextLine();
        try{
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);

//            String citizenAsJson=mapper.writeValueAsString( citizenHandler );
//            sendRequest( "citizen/insert/" + citizenAsJson );
//            String response= fromServer.readUTF();
//            System.out.println( response );
        }catch (Exception ex){}
    }

    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){}
    }

    public void login(String token){
        //calling login api
        String request = "citizen/getSingle/" + token;
        try{
            toServer.writeUTF(request);
            HouseHandler handler=mapper.readValue(fromServer.readUTF(),HouseHandler.class);
            System.out.println("HERE IS YOUR NAME" + handler.getName());

            if(handler.getName() != null) {
                //dashboard
                System.out.println("Successfully logged in!");
                Dashboard dashboard = new Dashboard(toServer, fromServer);
                dashboard.handleDashboard(fromServer, toServer, handler);
                return;
            }
            System.out.println("Invalid login, Try again!");
            return;
        }catch (IOException exception){
            System.out.println("Invalid login!");
            exception.printStackTrace();
            return;
        }
    }
}
