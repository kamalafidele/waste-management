package Components;

import DataHandlers.ConfirmerHandler;
import com.sun.nio.sctp.SendFailedNotification;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class ServiceConfirmationProcess {
    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner scanner;
    ObjectMapper mapper;

    public ServiceConfirmationProcess(DataOutputStream toServer,DataInputStream fromServer){
        this.toServer=toServer;
        this.fromServer=fromServer;
        scanner =new Scanner(System.in);
        mapper=new ObjectMapper();
    }
    public void sendRequest(String request){
        try{
            toServer.writeUTF(request);
        }catch(Exception e){
            e.getMessage();
        }
    }

    public boolean loginConfirmer(){
        var loginuser = new ConfirmerHandler();
        System.out.println("Enter your email");
        loginuser.setEmail(scanner.next());
        System.out.println("Enter your PIN");
        loginuser.setPin(scanner.nextInt());
        try{
            String loginasJSON= mapper.writeValueAsString(loginuser);
            sendRequest("serviceconfirmation/login"+loginasJSON);
            String response =fromServer.readUTF();
            if(response == "true") return true;
        }catch (Exception e){
            e.getMessage();
        }
        return  false;
    }
}
