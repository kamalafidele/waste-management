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

    public ResultSet findAll(int token){
        return database.select("SELECT * FROM Notification WHERE token =" + token);
    }
    public ResultSet findByViewStatus(String viewStatus, int token){
        return database.select("SELECT * FROM Notification WHERE viewStatus =" + viewStatus + "AND token =" + token);
    }

    public void save(Notification notification){
        database.insert("INSERT INTO Notification(token, notificationType, message, sentDate) VALUES("
                + notification.getToken() + "," + notification.getNotificationType() + "," + notification.getMessage()
                + "," + notification.getViewStatus() + "," + notification.getSentDate() + ")");
    }
}