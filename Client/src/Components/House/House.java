package Components.House;

import DataHandlers.CompanyHandler;
import DataHandlers.HouseHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class House extends Dashboard {

    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner keyboard = new Scanner(System.in);
    ObjectMapper mapper;
//    citizen/insert/{ "fullnames" : "karera marvin", "nid" : "12345678", "telno" : "indmts22", "telno" : "250788124399", "sector" : "niboye","cell" : "lorem", "village" : "indamutsa" }
//    citizen/getSingle/12349

    public House(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
        mapper = new ObjectMapper();
    }

    public void handleHouse(){
        System.out.print("\n");
        System.out.println("--------Login as a house owner!----------");
        System.out.print("Your pin: ");
        String token = keyboard.nextLine();

        //call login function
        login(token);
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
          if(handler.getFullnames() != null) {
              //dashboard
              System.out.println("Successfully logged in!");
              Dashboard dashboard = new Dashboard(toServer, fromServer);
              dashboard.handleDashboard(handler);
              return;
          }
          System.out.println("Invalid login, Try again!");
          return;
        }catch (IOException exception){
          System.out.println("Invalid login, Try again!");
          return;
        }
    }
}
