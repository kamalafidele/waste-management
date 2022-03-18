package Controllers;
import Models.*;
import Repositories.NotificationRepo;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        int receiver = parseInt(requestArray[2]);
        switch (requestArray[1]) {
            case "getAll" -> getAllNotifications(receiver);
            case "getUnread" -> getByViewStatusNotifications("unread", receiver);
            default -> sendResponse("Please specify your request (Be serious!)");
        }
    }

    private void insertNotificationsToList(List<Notification> notifications, ResultSet resultSet) {
        try{
            while(resultSet.next()){
                Notification notification = new Notification(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
                notifications.add(notification);
            }
            sendResponse(mapper.writeValueAsString(notifications));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllNotifications(int receiver){
        List<Notification> notifications = new ArrayList<>();
        ResultSet resultSet = notificationRepo.findAll(receiver);
        insertNotificationsToList(notifications, resultSet);
    }

    public void getByViewStatusNotifications(String viewStatus, int receiver){
        List<Notification> unreadNotifications = new ArrayList<>();
        ResultSet resultSet = notificationRepo.findByViewStatus(viewStatus, receiver);
        insertNotificationsToList(unreadNotifications, resultSet);
    }

    public void createNotification(int receiver, String notification_type){
        Notification notification = new Notification();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        notification.setSentDate(strDate);
        notification.setReceiver(receiver);


        switch (notification_type) {
            case "ServiceNotification" -> {
                notification.setType(1);
                notification.setContent("Hello! We are going to collect garbage after three days");
                notification.setTitle("Providing Service Soon");
            }
            case "providedServiceNotification" -> {
                notification.setType(4);
                notification.setContent("Hello! Your garbage was collected today");
                notification.setTitle("Service Provided");
            }
            case "paymentDueNotification" -> {
                notification.setType(5);
                notification.setContent("Hello! Three days remaining inorder to pay for garbage collection");
                notification.setTitle("Due Payment");
            }
            case "paymentWarningNotification" -> {
                notification.setType(3);
                notification.setContent("Hello! It's been a long time since you paid your garbage collection. If any further delay charges may apply");
                notification.setTitle("Payment Warning");
            }
            case "paymentSuccessfulNotification" -> {
                notification.setType(2);
                notification.setContent("Hello! Payment successful");
                notification.setTitle("Successful Payment");
            }
            case "reportAvailableNotification" -> {
                notification.setType(6);
                notification.setContent("Hello! View last months report(analytics)");
                notification.setTitle("Report Available");
            }
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