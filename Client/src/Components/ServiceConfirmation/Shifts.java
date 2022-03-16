package Components.ServiceConfirmation;

import DataHandlers.ShiftsHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Shifts {
      DataOutputStream toServer;
      DataInputStream fromServer;
      Scanner scanner;
      ObjectMapper mapper;
      public Shifts(DataOutputStream toServer,DataInputStream fromServer){
          this.toServer=toServer;
          this.fromServer=fromServer;
          scanner =new Scanner(System.in);
          mapper=new ObjectMapper();
      }

      public void addShift(){
          System.out.println("Request:");
          String request=scanner.nextLine();
          try{
              toServer.writeUTF(request);
              String response = fromServer.readUTF();
              System.out.println(response);
          }catch (Exception e){
              e.getMessage();
          }
      }
      public void sendRequest(String request){
          try{
              toServer.writeUTF(request);
          }catch(Exception e){
              e.getMessage();
          }
      }
      public void viewShifts(){
          String request="serviceConfirmation/shifts";
          try{
              sendRequest(request);
              String response = fromServer.readUTF();
              ArrayList<ShiftsHandler> shifts = mapper.readValue(response,new TypeReference<ArrayList<ShiftsHandler>>(){});
              Iterator<ShiftsHandler> shiftsIterator = shifts.iterator();
               while(shiftsIterator.hasNext()){
                   ShiftsHandler handler = shiftsIterator.next();
                   System.out.println("--------------Shift Id--------------");
                   System.out.println(handler.getId());
                   System.out.println("-----------------Company ID----------");
                   System.out.println(handler.getCompany_id());
                   System.out.println("---------------Date---------------");
                   System.out.println(handler.getDate());
                   System.out.println("-----------------Confirmer ID--------");
                   System.out.println(handler.getConfirmerId());
               }
          }catch (Exception e){
              e.getMessage();
          }

      }
    }


