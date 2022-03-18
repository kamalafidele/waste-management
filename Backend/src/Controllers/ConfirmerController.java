package Controllers;

import Models.Confirmer;
import Repositories.ConfirmerRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConfirmerController {
    private ConfirmerRepo confimerRepo;
    private DataOutputStream toClient;
    private ObjectMapper mapper;
    AnalyticsController analyticsController;

    public ConfirmerController(){
         confimerRepo = new ConfirmerRepo();
        analyticsController=new AnalyticsController();
        mapper=new ObjectMapper();
    }
    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient=toClient;
//        System.out.println("REQUEST RECEIVED: "+request.split("/")[1]);
    if (request.split("/")[1].equals("addConfirmer")){
        addConfirmer(request.split("/")[2]);
    }
    else{
        System.out.println("You thought u had no errors..... sikeeeeee!!!");
    }
    }

    public void addConfirmer(String data) {
        try{
            Confirmer confirmer=mapper.readValue(data,Confirmer.class);


            if(confimerRepo.save(confirmer))
                sendResponse("Confirmer added successfully");
            else
                sendResponse("Adding confirmer failed! Try again");
        }catch (Exception exception){
            sendResponse("Adding confirmer failed! Try again");
        }
    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
