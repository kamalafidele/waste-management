package Controllers;
import Models.*;
import Repositories.NotificationRepo;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import static java.lang.Integer.parseInt;

public class NotificationController {
    private DataOutputStream toClient;
    private final NotificationRepo notificationRepo;
    private final ObjectMapper mapper;

    public NotificationController(){
        notificationRepo = new NotificationRepo();
        mapper=new ObjectMapper();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest(String request, DataOutputStream toClient) {
        this.toClient=toClient;
        String[] requestArray = request.split("/");
        int token = parseInt(requestArray[2]);
        switch (requestArray[1]) {
            case "getAll" -> getAllNotifications(token);
            case "getUnread" -> getByViewStatusNotifications("unread", token);
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

    public void getAllNotifications(int token){
        List<Notification> notifications = new ArrayList<>();
        ResultSet resultSet = notificationRepo.findAll(token);
        insertNotificationsToList(notifications, resultSet);
    }

    public void getByViewStatusNotifications(String viewStatus, int token){
        List<Notification> unreadNotifications = new ArrayList<>();
        ResultSet resultSet = notificationRepo.findByViewStatus(viewStatus, token);
        insertNotificationsToList(unreadNotifications, resultSet);
    }

    public void createNotification(int token, String notificationType){
        Notification notification = new Notification();
        Date date = new Date();
        notification.setSentDate(date);
        notification.setToken(token);

        switch (notificationType) {
            case "ServiceNotification" -> notification.setMessage("Hello! We are going to collect garbage after three days");
            case "providedServiceNotification" -> notification.setMessage("Hello! Your garbage was collected today");
            case "paymentDueNotification" -> notification.setMessage("Hello! Three days remaining inorder to pay for garbage collection");
            case "paymentWarningNotification" -> notification.setMessage("Hello! It's been a long time since you paid your garbage collection. If any further delay charges may apply");
            case "paymentSuccessfulNotification" -> notification.setMessage("Hello! Payment successful");
            case "reportAvailableNotification" -> notification.setMessage("Hello! View last months report(analytics)");
            default -> System.out.println("Invalid notification type");
        }

        notificationRepo.save(notification);
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