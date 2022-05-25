package DataHandlers;

public class DebtHandler {
    private Integer id;
    private String serviceName;
    private Integer amount;
    private String date;

    public DebtHandler(){}
    public DebtHandler(Integer id, String serviceName, Integer amount, String date){
        this.id = id;
        this.serviceName = serviceName;
        this.amount = amount;
        this.date = date;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
