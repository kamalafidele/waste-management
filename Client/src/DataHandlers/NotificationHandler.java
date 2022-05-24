package DataHandlers;

import java.util.Date;
/*
 * @author: Fiat Bruno
 *
 */


public class NotificationHandler {
    private Long notificationId;
    private String title;
    private String content;
    private Integer receiver;
    private String type;
    private String viewStatus;
    private Date sentDate;

    public NotificationHandler(){

    }
    public NotificationHandler(Long notificationId, String title, String content, Integer receiver, String type, String viewStatus, Date sentDate){
        this.notificationId = notificationId;
        this.title = title;
        this.receiver = receiver;
        this.type = type;
        this.content = content;
        this.viewStatus = viewStatus;
        this.sentDate = sentDate;
    }
    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}