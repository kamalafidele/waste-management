package Models;

//<<<<<<< HEAD
import java.sql.Time;
import java.util.Date;

public class CustomerInvoices {
    private Long invoice_id ;
    private Date invoice_date;
    private Time generation_time;
    private String service_paid;
    private Long amount;
    private Integer user_id;

    public CustomerInvoices(){}

    public CustomerInvoices(Long invoice_id, Date invoice_date, Time generation_time, String service_paid, Long amount)
    {
        this.invoice_id = invoice_id;
        this.invoice_date = invoice_date;
        this.generation_time = generation_time;
        this.service_paid = service_paid;
        this.amount = amount;
    }

    public void setUser_id(Integer user_id) {this.user_id = user_id;}

//    public Long getInvoice_id() {
//        return invoice_id;
//    }

//    public void setInvoice_id(Integer invoice_id) {
//        this.invoice_id = Long.valueOf(invoice_id);
//    }

//=======
//import java.sql.Date;
//import java.sql.Time;

//public class CustomerInvoices {
//    private Integer invoice_id ;

//    public Integer getUserId() {
//        return userId;
//    }

    public void setUserId(Integer userId) {
        this.user_id = userId;
    }

//    private Integer userId;
//    private Date invoice_date;
//    private Time generation_time;
//    private String service_paid;
//    private Integer amount;

//    public void CustomerInvoices(Integer invoice_id,Integer userId,Date invoice_date, Time generation_time,String service_paid,Integer amount){
//        this.invoice_id=invoice_id;
//        this.invoice_date=invoice_date;
//        this.generation_time=generation_time;
//        this.service_paid=service_paid;
//        this.amount=amount;
//        this.userId=userId;
//    }

//    public Integer getInvoice_id() {
//        return invoice_id;
//    }

//>>>>>>> e39bdb80294deb1684f4d1aec412203a57fe1d09
    public Date getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }
//
//<<<<<<< HEAD
//=======
    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }
//>>>>>>> e39bdb80294deb1684f4d1aec412203a57fe1d09
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
