package Repositories;
/*
 *
 * @author: Fiat Bruno
 *
 */

import Config.DatabaseConnection;
import Models.Notification;

import java.sql.ResultSet;


public class NotificationRepo{
    DatabaseConnection database;
    public NotificationRepo(){database = new DatabaseConnection(); }

    public ResultSet findAll(){
        return database.select("SELECT * FROM Notification");
    }
    public ResultSet findByViewStatus(String viewStatus){
        return database.select("SELECT * FROM Notification WHERE viewStatus = " + viewStatus);
    }

    public void save(Notification notification){
        database.insert("INSERT INTO Notification(userId, notificationType, message, sentDate) VALUES("
                + notification.getToken() + "," + notification.getNotificationType() + "," + notification.getMessage()
                + "," + notification.getViewStatus() + "," + notification.getSentDate() + ")");
    }
}