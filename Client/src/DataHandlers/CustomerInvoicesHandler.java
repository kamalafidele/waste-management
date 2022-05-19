package DataHandlers;

import java.sql.Time;
import java.util.Date;

public class CustomerInvoicesHandler {
    private Long invoice_id ;
    private Date invoice_date;
    private Time generation_time;
    private String service_paid;
    private Long amount;
    private Integer user_id;


    public void setUser_id(Integer user_id) {this.user_id = user_id;}

    public int getUser_id(){return user_id;}

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = Long.valueOf(invoice_id);
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) { this.amount = amount; }
}
