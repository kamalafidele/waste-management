package Components.District;

import Components.Company;
import Components.House.House;
import Components.Wallet;

import DataHandlers.CompanyHandler;
import DataHandlers.DistrictHandler;
import DataHandlers.LoginData;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


import java.io.*;
import java.net.Socket;
import java.util.*;

public class DistrictDashboard {

    LoginData logindata;
    Company company;
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;
    Scanner keyboard;
    FileInputStream fileIn;
    FileOutputStream fileOut;
    InputStreamReader streamReader;
    BufferedReader bufferedReader;
    File file;

    public DistrictDashboard(){
        mapper = new ObjectMapper();
    }

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
            System.out.print("District email: ");
            logindata.setEmail(keyboard.next());
            System.out.print("Password: ");
            logindata.setPin(Long.valueOf(keyboard.next()));


            toServer.flush();
            this.sendRequest("district/login/" + mapper.writeValueAsString(logindata));


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
    public void districtAdd() {

        var districtHandler=new DistrictHandler();
         keyboard = new Scanner(System.in);
         System.out.println( "######## District Addition#########" );
         System.out.print( "Enter district Name: " );
         String name= keyboard.nextLine();
         districtHandler.setName(name);
         System.out.print( "Enter district Email: " );
        String email= keyboard.nextLine();
        districtHandler.setEmail(email);
        System.out.print( "Enter district Phone: " );
        String phone= keyboard.nextLine();

        Random random = new Random();
        long pin = random.nextLong( 500_000_000 );
        districtHandler.setWalletId( 0L );


        try{
            String districtAsJson=mapper.writeValueAsString( districtHandler );
            sendRequest( "district/addDistrict/" + districtAsJson );
            String response= fromServer.readUTF();
            System.out.println( response );
        }catch (IOException exception){}
    }

//    public void displayDistricts(){
//        String request="district/getDistricts";
//
//        try{
//            sendRequest(request);
//            String response=fromServer.readUTF();
//            ArrayList<DistrictHandler> districts=mapper.readValue(response,new TypeReference<ArrayList<DistrictHandler>>(){});
//            Iterator<DistrictHandler> districtIterator=districts.iterator();
//
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> All Districts <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
//            System.out.println("|------------|----------------------------------|-----------------------------------|");
//            System.out.println("|    No      |        District Email            |         District pin              |");
//            System.out.println("|------------|----------------------------------|-----------------------------------|");
//            while (districtIterator.hasNext()){
//
//                DistrictHandler handler=districtIterator.next();
//                String space="";
//                int idSpaceCount=12;
//                String idSpace="";
//                for(int j=0; j<idSpaceCount-2; j++){
//                    idSpace+=" ";
//                }
//                for(int i=0;i<18-handler.getEmail().length(); i++){
//                    space+=" ";
//                }
//                System.out.println("| "+handler.getEmail()+idSpace+"|"+(handler.getEmail().length() <= 18 ? handler.getEmail()+space : handler.getEmail().substring(0,18))
//                        +"                |"+handler.getName()+"   ");
//                System.out.println("|------------|----------------------------------|-----------------------------------|");
//            }
//
//        }catch (IOException ex){}
//    }
public void displayDistricts(){
    String request="district/getDistricts";

    try{
        sendRequest(request);
        String response=fromServer.readUTF();
        ArrayList<DistrictHandler> districts=mapper.readValue(response,new TypeReference<ArrayList<DistrictHandler>>(){});
        Iterator<DistrictHandler> districtIterator=districts.iterator();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> All Districts <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
        System.out.println("|------------|----------------------------------|-----------------------------------|");
        System.out.println("|    No       |        District Token           |       |   District Name           |");
        System.out.println("|------------|----------------------------------|-----------------------------------|");
        while (districtIterator.hasNext()){
            DistrictHandler handler=districtIterator.next();
            String space="";
            int idSpaceCount=12;
            String idSpace="";
            for(int j=0; j<idSpaceCount-2; j++){
                idSpace+=" ";
            }
            for(int i=0;i<18-handler.getEmail().length(); i++){
                space+=" ";
            }
            System.out.println("| "+handler.getId()+idSpace+"|"+(handler.getEmail().length() <= 18 ? handler.getEmail()+space : handler.getEmail().substring(0,18))
                    +"                |"+handler.getName()+"   ");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
        }

    }catch (IOException ex){}
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
                System.out.println("--------Invalid Inputs----------");
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

            System.out.println("2. Creating Company");
            System.out.println("3. Citizen Registration");


            int choice;
            System.out.print("Choose: ");
            choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    System.out.println(" Creating Company");
                    Company company=new Company(toServer,fromServer);
                    company.addCompany();
                    break;
                case 2:
                    System.out.println("Citizen Registration");
                    House house=new House(toServer,fromServer);
                    house.addCitizen();
                    break;

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    

}
