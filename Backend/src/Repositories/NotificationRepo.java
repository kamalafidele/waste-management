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

    public ResultSet findAll(int receiver){
        return database.select("SELECT * FROM notifications WHERE receiver =" + receiver);
    }
    public ResultSet findByViewStatus(String viewStatus, int receiver){
        return database.select("SELECT * FROM notifications WHERE viewStatus =" + viewStatus + "AND receiver =" + receiver);
    }

    public void save(Notification notification){
        database.insert("INSERT INTO notifications(Title, Content, Receiver, Type, sentDate) VALUES("
                + notification.getReceiver() + "," + notification.getType() + "," + notification.getContent()
                + "," + notification.getViewStatus() + "," + notification.getSentDate() + ")");
    }
}