package Models;
/*
*
* @author: Fiat Bruno
*
*/

import java.util.Date;

public class Notification {
    private Long notificationId;
    private Integer token;
    private String notificationType;
    private String message;
    private String viewStatus;
    private Date sentDate;

    public Notification(){

    }
    public Notification(Long notificationId, Integer token, String notificationType, String message, String viewStatus, Date sentDate){
        this.notificationId = notificationId;
        this.token = token;
        this.notificationType = notificationType;
        this.message = message;
        this.viewStatus = viewStatus;
        this.sentDate = sentDate;
    }
    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

}