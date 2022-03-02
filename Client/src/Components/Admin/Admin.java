package Components.Admin;

import DataHandlers.Admin.LoginInfo;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Admin {
    LoginInfo loginInfos;
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;
    Scanner keyboard;
    FileInputStream fileIn;
    FileOutputStream fileOut;
    InputStreamReader streamReader;
    BufferedReader bufferedReader;
    File file;

    public Admin(){}

    public Admin(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer=fromServer;
        mapper = new ObjectMapper();
        keyboard = new Scanner(System.in);
        file = new File("loggedIn.txt");
    }

    public void handleAdmin(){
        //check if user is logged in
        boolean loggedIn = loggedIn();

        if(!loggedIn){
         login();
        }
    }

    public void login()  {
        try {
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
            this.sendRequest("admin/login/" + mapper.writeValueAsString(loginInfos));
            String response = fromServer.readUTF();
            System.out.println(response);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public boolean loggedIn(){
        //checking if admin is loggedIn
        try {

            if(!file.exists()){
                boolean createFile = file.createNewFile();
                String data = "false";
                fileOut = new FileOutputStream(file);
                fileOut.write(data.getBytes(), 0, data.length());
                return false;
            }else{
                fileIn = new FileInputStream(file);
                streamReader = new InputStreamReader(fileIn);
                bufferedReader = new BufferedReader(streamReader);
                String content = bufferedReader.readLine();

                return !Objects.equals(content, "false");
            }

        }catch(IOException e){
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
