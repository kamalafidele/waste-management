package Controllers;

import Models.Notification;
import Repositories.NotificationRepo;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationController {
    private DataOutputStream toClient;
    private final NotificationRepo notificationRepo;
    private final ObjectMapper mapper;

    public NotificationController(){
        notificationRepo = new NotificationRepo();
        mapper=new ObjectMapper();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest( String request, DataOutputStream toClient ) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "getAll" -> getAllNotifications();
            case "getUnread" -> getByViewStatusNotifications("unread");
            default -> sendResponse("Please specify your request (Be serious!)");
        }
    }

    private void insertNotificationsToList(List<Notification> notifications, ResultSet resultSet) {
        try{
            while(resultSet.next()){
                Notification notification = new Notification(resultSet.getLong(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6));
                notifications.add(notification);
            }
            sendResponse(mapper.writeValueAsString(notifications));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllNotifications(){
        List<Notification> notifications = new ArrayList<>();
        ResultSet resultSet = notificationRepo.findAll();
        insertNotificationsToList(notifications, resultSet);
    }

    public void getByViewStatusNotifications(String viewStatus){
        List<Notification> unreadNotifications = new ArrayList<>();
        ResultSet resultSet = notificationRepo.findByViewStatus(viewStatus);
        insertNotificationsToList(unreadNotifications, resultSet);
    }

    public void ServiceNotification(){

    }
    public void providedServiceNotification(){

    }
    public void paymentDueNotification(){

    }
    public void paymentWarningNotification(){

    }
    public void paymentSuccessfulNotification(){

    }
    public void reportAvailableNotification(){

    }
    // THIS A METHOD FOR SENDING
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

}