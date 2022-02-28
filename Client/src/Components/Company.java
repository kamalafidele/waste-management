package Components;

import DataHandlers.CompanyHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// THIS COMPANY COMPONENT
public class Company {
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;

    public Company() {
        mapper=new ObjectMapper();
    }

    public void displayCompanies(DataOutputStream toServer, DataInputStream fromServer){
        this.toServer=toServer;
        this.fromServer=fromServer;
        String request="company/getAll/none";

        try{
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            ArrayList<CompanyHandler> companies=mapper.readValue(response,new TypeReference<ArrayList<CompanyHandler>>(){});
            Iterator<CompanyHandler> companyIterator=companies.iterator();

            System.out.println("######################### REGISTERED COMPANIES ###################################### ");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            System.out.println("|    #       |        EMAIL                     |             NAME                  |");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            while (companyIterator.hasNext()){
                CompanyHandler handler=companyIterator.next();
            System.out.println("| "+handler.getId()+"          |"+(handler.getEmail().length() <= 18 ? handler.getEmail() : handler.getEmail().substring(0,18))
                    +"                |"+handler.getName()+"   ");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            }

        }catch (IOException ex){}
    }
}
