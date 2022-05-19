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
    FileWriter writer;
    FileReader reader;
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
                    writer = new FileWriter(file);
                    writer.write(loginRes);
                    writer.flush();
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
            switch (choice) {
                    case 1:
                        showDistricts();
                        break;
                    case 2:
                        showWallet();
                        break;
                    case 3:
                        showAnalytics();
                        break;
                    case 4:
                        createAdmin();
                        break;
                    case 5:
                        createDistrict();
                        break;
                    case 6:
                        logout();
                        break;
                    default:
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
                file.createNewFile();
                return false;
            }else{
                if(file.length() > 0){
                    return true;
                }else{
                    return false;
                }
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
        System.out.println("--------Create another admin!----------");
        System.out.println("name: ");
        System.out.println("email: ");
        System.out.println("Phone: ");
    }

    public  void createDistrict(){
        System.out.println("Add District");
        DistrictDashboard districtDashboard2=new DistrictDashboard(toServer,fromServer);
        districtDashboard2.districtAdd();
    }

    public void showAnalytics(){

    }

    public void showWallet() throws IOException {
        String id = new BufferedReader(new FileReader(file)).readLine();
        new Wallet(toServer,fromServer).showWallet(Integer.parseInt(id));
    }

    public void showDistricts() throws IOException {
        DistrictDashboard districtDashboard=new DistrictDashboard(toServer,fromServer);
        districtDashboard.displayDistricts();
    }

    public void logout() throws IOException {
        new FileWriter("loggedIn.txt", false).close();
    }

}
