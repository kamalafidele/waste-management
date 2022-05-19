package DataHandlers;

public class ShiftsHandler {
    private int id;
    private int company_id;
    private String date;
    private int confirmerId;

    public ShiftsHandler(){

    }

    public ShiftsHandler(int id,int company_id,String date){
        this.id=id;
        this.company_id=company_id;
        this.date=date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getConfirmerId() {
        return confirmerId;
    }

    public void setConfirmerId(int confirmerId) {
        this.confirmerId = confirmerId;
    }

}
