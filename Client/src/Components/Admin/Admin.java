package Components.Admin;

import Components.District.DistrictDashboard;
import Components.Wallet;
import DataHandlers.Admin.LoginInfo;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.*;
import java.net.Socket;
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

    public Admin(){
        mapper = new ObjectMapper();
    }

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
            String loginRes = login();

            if(Objects.equals(loginRes, "false")){
                System.out.println("--------Invalid credentials!----------");
                System.out.println("\n");
            }else{
                try {
                    //record that admin is loggedIn
                    String data = "true";
                    fileOut = new FileOutputStream(file);
                    fileOut.write(data.getBytes(), 0, data.length());
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
//            Socket socket=new Socket("localhost",3000);
//            DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
//            DataInputStream fromServer=new DataInputStream(socket.getInputStream());
            //admin dashboard
            System.out.println("\n");
            System.out.println("--------Welcome abroad!----------");

            //admin dashboard
            System.out.println("1. Check districts");
            System.out.println("2. Your wallet");
            System.out.println("3. Your analytics");
            System.out.println("4. Add district");
            System.out.println("5. logout");

            //choose
            int choice;
            System.out.print("Choose: ");
            choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    System.out.println("see districts");
                    break;
                case 2:
                    new Wallet(toServer,fromServer).showWallet();
                    break;
                case 3:
                    System.out.println("see analytics");
                    break;
                case 4:
                    System.out.println("Add District");
                    DistrictDashboard districtDashboard=new DistrictDashboard(toServer,fromServer);
                    districtDashboard.addDistrict();
                    break;
                case 5:
                    System.out.println("logout");
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String login()  {
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
            toServer.flush();
            this.sendRequest("admin/login/" + mapper.writeValueAsString(loginInfos));

            return fromServer.readUTF();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "false";
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
