package Components;

import DataHandlers.CompanyHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

// THIS COMPANY COMPONENT
public class Company {
    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner keyboard;
    ObjectMapper mapper;
    Shifts shifts;

    public Company(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
        keyboard = new Scanner(System.in);
        mapper = new ObjectMapper();
        shifts = new Shifts(toServer,fromServer);
    }

    public void login(){
        CompanyHandler handler = new CompanyHandler();
        System.out.println("########### LOGIN AS A COMPANY #############");
        System.out.print("Enter email: ");
        handler.setEmail(keyboard.nextLine());
        System.out.print("Enter pin: ");
        handler.setPin(keyboard.nextLong());
        try{
            String request = "company/login/"+mapper.writeValueAsString(handler);
            sendRequest(request);

            String response = fromServer.readUTF();

            if(response.equals("Invalid Pin or Email")) {
                System.out.println(response);
                System.exit(0);
            }else {
                CompanyHandler company = mapper.readValue(response,CompanyHandler.class);
                int choice = 0;
                System.out.println("YOU HAVE SUCCESSFULLY LOGGED IN");
                System.out.println();
                System.out.println("### YOUR PROFILE ###");
                System.out.println("NAME: " + company.getName());
                System.out.println("EMAIL: " + company.getEmail());
                System.out.println("PHONE: " + company.getPhone());
                System.out.println("PIN: " + company.getPin());
                System.out.println("COMPANY ID: " + company.getId());

                System.out.println();

                System.out.println("---- CHOOSE WHAT TO DO ------------");
                System.out.println("1. CREATE A SHIFT ");
                System.out.println("0. EXIT ");
                System.out.print("Enter your choice: ");
                choice = keyboard.nextInt();

                switch (choice){
                    case 1:
                        shifts.addShift();
                      break;
                    case 0:
                        System.exit(0);
                      break;
                    default:
                        System.out.println("CHOOSE FROM AVAILABLE OPTIONS PLEASE");
                      break;
                }

            }

        } catch (IOException exception){}

    }

    public void displayCompanies(){
       String request="company/getAll/none";

        try{
            sendRequest(request);
            String response = fromServer.readUTF();
            ArrayList<CompanyHandler> companies = mapper.readValue(response,new TypeReference<ArrayList<CompanyHandler>>(){});
            Iterator<CompanyHandler> companyIterator = companies.iterator();

            System.out.println("######################### REGISTERED COMPANIES ###################################### ");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            System.out.println("|    #       |        EMAIL                     |             NAME                  |");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            while (companyIterator.hasNext()){
                CompanyHandler handler=companyIterator.next();
            String space="";
            int idSpaceCount=12-calculateSpace(handler.getId());
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

    public void addCompany() {

        var companyHandler=new CompanyHandler();
        System.out.println( "######## ADDING NEW COMPANY #########" );
        System.out.print( "Enter company name: " );
        companyHandler.setName( keyboard.nextLine() );
        System.out.print( "Enter company email: " );
        companyHandler.setEmail( keyboard.nextLine() );
        System.out.print( "Enter company phone: " );
        companyHandler.setPhone( keyboard.nextLine() );
        
        Random random = new Random();
        long pin = random.nextLong( 500_000_000 );
        companyHandler.setPin( pin );
        companyHandler.setRole( 2 );
        companyHandler.setWalletId( 0 );

        try{
            String companyAsJson = mapper.writeValueAsString( companyHandler );
            sendRequest( "company/addCompany/" + companyAsJson );
            String response= fromServer.readUTF();
            System.out.println( response );
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void createCompanyDistrictContract( int districtId, int companyId ) {
        String requestData  = districtId+"-"+companyId;
        String request = "company/createContract/"+requestData;
        sendRequest(request);
        try{
            String response = fromServer.readUTF();
            System.out.println(response);
        }catch ( IOException exception){}
    }

    public int calculateSpace(int num){
        if(num >= 10 && num <100)
            return 1;
        else if(num >= 100)
            return 2;
        else
            return 0;
    }

    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){}
    }

}
