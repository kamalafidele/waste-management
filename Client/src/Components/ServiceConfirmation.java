package Components;

import DataHandlers.ServiceConfirmationHandler;
import DataHandlers.ShiftsHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class ServiceConfirmation {
    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner scanner;
    ObjectMapper mapper;

    public ServiceConfirmation(DataOutputStream toServer,DataInputStream fromServer){
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

    public void viewConfirmedService(){
        String request="serviceconfirmation/viewconfirmedservice";
        try{
            sendRequest(request);
            String response = fromServer.readUTF();
            ArrayList<ServiceConfirmationHandler> confirmedServices = mapper.readValue(response,new TypeReference<ArrayList<ServiceConfirmationHandler>>(){});
            Iterator<ServiceConfirmationHandler> confirmedServicesIterator = confirmedServices.iterator();
            System.out.println("---------Confirmed Services-------");
            System.out.println(" ConfirmedService ID | Service ID | Shift ID  | Confirmer ID |");
            while(confirmedServicesIterator.hasNext()){
                ServiceConfirmationHandler handler = confirmedServicesIterator.next();


                System.out.println(" | "+ handler.getId()+" | "+"      | "+handler.getServiceId()+" | "+"     | "+handler.getShiftId()+" | "+"       | "+handler.getConfirmerId()+" | ");
            }
        }catch (Exception e){
            e.getMessage();
        }

    }

    public void addConfirmedService(){
        System.out.println("request:");
        var confirmedService = new ServiceConfirmationHandler();
        System.out.println("------------------ADDING NEW SHIFT--------------");
        System.out.println("---Enter the ID of the Company working in the area---");
        confirmedService.setServiceId(scanner.nextInt());
        System.out.println("--Enter the date ---- ");
        confirmedService.setShiftId(scanner.nextInt());
        System.out.println("--Enter your ID---");
        confirmedService.setConfirmerId(scanner.nextInt());
        try{
            String confirmedserviceasJSon= mapper.writeValueAsString(confirmedService);
            sendRequest("serviceconfirmation/addConfirmedService/"+confirmedserviceasJSon);
            String response = fromServer.readUTF();
            System.out.println(response);
        }catch (Exception e){
            e.getMessage();
        }
    }

}
