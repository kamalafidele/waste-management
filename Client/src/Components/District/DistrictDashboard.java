package Components.District;

import Components.Wallet;

import DataHandlers.LoginData;


import org.codehaus.jackson.map.ObjectMapper;


import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class DistrictDashboard {

    LoginData logindata;

    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;
    Scanner keyboard;
    FileInputStream fileIn;
    FileOutputStream fileOut;
    InputStreamReader streamReader;
    BufferedReader bufferedReader;
    File file;

    public DistrictDashboard(){}

    public DistrictDashboard(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer=fromServer;
        mapper = new ObjectMapper();
        keyboard = new Scanner(System.in);
        file = new File("isLogged.txt");
    }
    public String login()  {
        try {


            logindata = new LoginData();

            System.out.print("\n");
            System.out.println("-------- District Login!----------");
            System.out.print("districtToken: ");

            logindata.setDistrictToken(keyboard.next());
            System.out.print("Password: ");
            logindata.setPassword(keyboard.next());


            toServer.flush();
            this.sendRequest("District/login/" + mapper.writeValueAsString(logindata));


            return fromServer.readUTF();

        }catch (Exception e){
            e.printStackTrace();
        }

        return "false";

    }

    public boolean isLogged(){                                                                                                                                   

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
    public void handleDistrict(){
   
        boolean isLogged = isLogged();

        if(!isLogged){
            String loginRes = login();

            if(Objects.equals(loginRes, "false")){
                System.out.println("--------Invalid credentials!----------");
                System.out.println("\n");
            }else{
                try {
                    String data = "true";
                    fileOut = new FileOutputStream(file);
                    fileOut.write(data.getBytes(), 0, data.length());
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            System.out.println("\n");
            System.out.println("--------Welcome abroad!----------");


            System.out.println("1.User registration");
            System.out.println("2. Creating Company");
            System.out.println("3. Confirmer");
            System.out.println("3. Citizen Registration");

            int choice;
            System.out.print("Choose: ");
            choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    System.out.println("User registration");
                    break;
                case 2:
                    System.out.println(" Creating Company");
                    break;
                case 3:
                    System.out.println("Confirmer");
                    break;
                    case 4:
                    System.out.println("Citizen Registration");
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    

}