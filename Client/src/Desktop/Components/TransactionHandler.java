package Desktop.Components;

import java.util.Date;

public class TransactionHandler {
    private Long id;
    private String money;
    private String service;
    private Date date;

    public TransactionHandler() {
    }

    public TransactionHandler(Long id, String money, String service,Date date) {
        this.id = id;
        this.money = money;
        this.service = service;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }

    public String getService() {return service;}
    public void setService(String service) { this.service = service; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

}
