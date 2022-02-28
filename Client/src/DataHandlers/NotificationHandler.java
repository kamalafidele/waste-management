package DataHandlers;
/*
 *
 * @author: Fiat Bruno
 *
 */

import java.util.Date;

public class NotificationHandler {
    private Long notificationId;
    private Integer userId;
    private String notificationType;
    private String message;
    private String viewStatus;
    private Date sentDate;

    public NotificationHandler(){

    }
    public NotificationHandler(Long notificationId, Integer userId, String notificationType, String message, String viewStatus,Date sentDate){
        this.notificationId = notificationId;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
