package Components;

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

      public void sendRequest(String request){
          try{
              toServer.writeUTF(request);
          }catch(Exception e){
              e.getMessage();
          }
      }
      public void viewShifts(){
          String request="serviceConfirmation/getShifts";
          try{
              sendRequest(request);
              String response = fromServer.readUTF();
              ArrayList<ShiftsHandler> shifts = mapper.readValue(response,new TypeReference<ArrayList<ShiftsHandler>>(){});
              Iterator<ShiftsHandler> shiftsIterator = shifts.iterator();
               while(shiftsIterator.hasNext()){
                   ShiftsHandler handler = shiftsIterator.next();
                   System.out.print("--------------Shift Id--------------");
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

      public void addShift(){
          System.out.println("request:");
//          String
          var shiftsHandler = new ShiftsHandler();
          System.out.println("------------------ADDING NEW SHIFT--------------");
          System.out.println("---Enter the ID of the Company working in the area---");
          shiftsHandler.setCompany_id(scanner.nextInt());
          System.out.println("--Enter the date ---- ");
          shiftsHandler.setDate(scanner.next());
          System.out.println("--Enter your ID---");
          shiftsHandler.setConfirmerId(scanner.nextInt());
          try{
              String shiftasJSon= mapper.writeValueAsString(shiftsHandler);
              sendRequest("serviceconfirmation/addShift/"+shiftasJSon);
              String response = fromServer.readUTF();
              System.out.println(response);
          }catch (Exception e){
              e.getMessage();
          }
      }
    }


