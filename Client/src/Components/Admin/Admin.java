package Components.Admin;


import Components.District.DistrictDashboard;
import Components.Wallet;
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

    public void handleAdmin() {
        //check if user is logged in
        boolean loggedIn = loggedIn();

        try {
            if (!loggedIn) {
                String loginRes = login();

                if (Objects.equals(loginRes, "false")) {
                    System.out.println("--------Invalid credentials!----------");
                    System.out.println("\n");
                    return;
                } else {
                    try {
                        //record that admin is loggedIn
                        String data = "true";
                        fileOut = new FileOutputStream(file);
                        fileOut.write(data.getBytes(), 0, data.length());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }

            //admin dashboard
            System.out.println("\n");
            System.out.println("--------Welcome abroad!----------");

            //admin dashboard
            System.out.println("1. Check districts");
            System.out.println("2. Your wallet");
            System.out.println("3. Your analytics");
            System.out.println("4. Create new admin");
            System.out.println("5. Add district");
            System.out.println("6. logout");

            //choose
            int choice;
            System.out.print("Choose: ");
            choice = keyboard.nextInt();
            switch (choice){
                case 1:

                    DistrictDashboard districtDashboard=new DistrictDashboard(toServer,fromServer);
                    districtDashboard.displayDistricts();
                    break;
                case 2:
                    new Wallet(toServer,fromServer).showWallet();
                    break;
                case 3:
                    System.out.println("see analytics");
                    break;
                case 4:
                    System.out.println("Add admin");

                    break;
                case 5:
                    System.out.println("Add District");
                    DistrictDashboard districtDashboard2=new DistrictDashboard(toServer,fromServer);
                    districtDashboard2.districtAdd();
                    break;
                case 6:
                    System.out.println("logout");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String login() throws IOException {
            //create login info object
            loginInfos = new LoginInfo();

            //get inputs from user
            System.out.print("\n");
            System.out.println("--------Login as an admin!----------");
            System.out.print("name: ");
            loginInfos.setName(keyboard.next());
            System.out.print("Pin: ");
            loginInfos.setPassword(keyboard.next());

            //call login function
            toServer.flush();
            this.sendRequest("admin/login/" + mapper.writeValueAsString(loginInfos));

            //return the response
            return fromServer.readUTF();

    }

    public boolean loggedIn(){

        try {
            //checking if file exists
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

                return Objects.equals(content, "true");
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

    public void createAdmin(){

    }

    public void showAnalytics(){

    }

    public void showWallet() throws IOException {
        new Wallet(toServer,fromServer).showWallet();
    }

    public void showDistricts() throws IOException {
        toServer.flush();
        this.sendRequest("admin/login/" + mapper.writeValueAsString(loginInfos));
    }

    public void logout() throws IOException {
        String data = "false";
        fileOut = new FileOutputStream(file);
        fileOut.write(data.getBytes(), 0, data.length());
    }

}
