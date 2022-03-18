package Components;

import DataHandlers.ConfirmerHandler;
import com.sun.nio.sctp.SendFailedNotification;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    public String loginConfirmer() throws IOException {
        var loginuser = new ConfirmerHandler();
        System.out.println("--login--");
        System.out.println("Enter your email");
        loginuser.setEmail(scanner.next());
        System.out.println("Enter your PIN");
        loginuser.setPin(scanner.nextInt());

            String loginasJSON= mapper.writeValueAsString(loginuser);
            sendRequest("serviceconfirmation/login/"+loginasJSON);
            String response =fromServer.readUTF();
            System.out.println("response:"+ response);

      return response;
    }
}
