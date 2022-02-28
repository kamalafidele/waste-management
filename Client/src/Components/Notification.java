package Components;
/*
 *
 * @author: Fiat Bruno
 *
 */

import DataHandlers.NotificationHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Notification {
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectMapper mapper;

    public Notification() {
        mapper=new ObjectMapper();
    }

    public void displayAllNotifications(DataOutputStream toServer, DataInputStream fromServer){
        this.toServer=toServer;
        this.fromServer=fromServer;
        String request="notification/getAll/none";

        try{
            toServer.writeUTF(request);
            String response=fromServer.readUTF();
            ArrayList<NotificationHandler> notifications=mapper.readValue(response,new TypeReference<ArrayList<NotificationHandler>>(){});
            Iterator<NotificationHandler> notificationIterator= notifications.iterator();

            System.out.println("########################### ALL NOTIFICATIONS #######################################");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            System.out.println("|    #       |        TYPE                      |             MESSAGE               |");
            System.out.println("|------------|----------------------------------|-----------------------------------|");
            while (notificationIterator.hasNext()){
                NotificationHandler handler=notificationIterator.next();
                System.out.println("| "+handler.getNotificationType()+"          |"+(handler.getMessage().length() <= 18 ? handler.getMessage() : handler.getMessage().substring(0,18))
                        +"                |"+handler.getViewStatus()+"   ");
                System.out.println("|------------|----------------------------------|-----------------------------------|");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
