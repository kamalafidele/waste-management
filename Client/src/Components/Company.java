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

    public Company(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer=toServer;
        this.fromServer=fromServer;
        keyboard=new Scanner(System.in);
        mapper=new ObjectMapper();
    }

    public void addCitizen(){
        System.out.println("request: ");
        String request = keyboard.nextLine();
        try{
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            System.out.println(response);
        }catch (Exception ex){}
    }

    public void displayCompanies(){
       String request="company/getAll/none";

        try{
            sendRequest(request);
            String response=fromServer.readUTF();
            System.out.println(response);
            ArrayList<CompanyHandler> companies=mapper.readValue(response,new TypeReference<ArrayList<CompanyHandler>>(){});
            Iterator<CompanyHandler> companyIterator=companies.iterator();

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
//        long pin = random.nextLong( 500_000_000 );
        long pin = 1234567890;
        companyHandler.setPin( pin );
        companyHandler.setRole( 2 );
        companyHandler.setWalletId( 0 );

        try{
            String companyAsJson=mapper.writeValueAsString( companyHandler );
            sendRequest( "company/addCompany/" + companyAsJson );
            String response= fromServer.readUTF();
            System.out.println( response );
        }catch (IOException exception){}
    }

    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){}
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
}
