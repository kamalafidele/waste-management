package Components.Admin;

import DataHandlers.Admin.LoginInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Admin {
    LoginInfo loginInfos;
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;
    Scanner keyboard;

    public Admin(){}

    public Admin(DataOutputStream toServer, DataInputStream fromServer){
        this.toServer = toServer;
        this.fromServer=fromServer;
        mapper = new ObjectMapper();
        keyboard = new Scanner(System.in);
    }

    public void handleAdmin(){
        //check if user is logged in
        boolean loggedIn = loggedIn();

        if(!loggedIn){
         login();
        }
    }

    public void login(){
        //create login info object
        loginInfos = new LoginInfo();

        //get inputs from user
        System.out.print("\n");
        System.out.println("--------Login as an admin!----------");
        System.out.print("Username: ");
        loginInfos.setUsername(keyboard.next());
        System.out.print("Password: ");
        loginInfos.setPassword(keyboard.next());

        //call login function
        this.sendRequest("/admin/login" + loginInfos);

    }

    public boolean loggedIn(){
        //checking if admin is loggedIn
        try {
            Path filename =  Path.of("jetbrains://idea/navigate/reference?project=Client&path=Components/Admin/loggedIn.txt");
            String fileContent = Files.readString(filename);

            if(Objects.equals(fileContent, "true")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void sendRequest(String request){
        try {
            this.toServer.writeUTF(request);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
