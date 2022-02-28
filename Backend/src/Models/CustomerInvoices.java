package Models;

import java.sql.Date;
import java.sql.Time;

public class CustomerInvoices {
    private Integer invoice_id ;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;
    private Date invoice_date;
    private Time generation_time;
    private String service_paid;
    private Integer amount;

    public void CustomerInvoices(Integer invoice_id,Integer userId,Date invoice_date, Time generation_time,String service_paid,Integer amount){
        this.invoice_id=invoice_id;
        this.invoice_date=invoice_date;
        this.generation_time=generation_time;
        this.service_paid=service_paid;
        this.amount=amount;
        this.userId=userId;
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }
    public Time getGeneration_time() {
        return generation_time;
    }

    public void setGeneration_time(Time generation_time) {
        this.generation_time = generation_time;
    }

    public String getService_paid() {
        return service_paid;
    }

    public void setService_paid(String service_paid) {
        this.service_paid = service_paid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
