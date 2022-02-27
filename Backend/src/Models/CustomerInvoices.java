package Models;

public class CustomerInvoices {
    private Integer invoice_id ;
    private String invoice_date;
    private Integer generation_time;
    private String service_paid;
    private Integer amount;

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public Integer getGeneration_time() {
        return generation_time;
    }

    public void setGeneration_time(Integer generation_time) {
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
