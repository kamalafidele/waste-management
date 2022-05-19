package Models;

import java.util.Date;
/*
 * @author: Fiat Bruno
 *
 */


public class Notification {
    private Long notificationId;
    private String title;
    private String content;
    private Integer receiver;
    private Integer type;
    private String viewStatus;
    private String sentDate;

    public Notification(){

    }
    public Notification(Long notificationId, String title, String content, Integer receiver, Integer type, String viewStatus, String sentDate){
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}









