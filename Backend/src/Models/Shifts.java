package Models;


public class Shifts {
    private Integer id;
    private Integer company_id;
    private String date;
    private Integer confirmerId;
    private String company_name;

    public Shifts(){

    }

    public Shifts(Integer id,Integer company_id,String date,Integer confirmerId,String company_name){
        this.id=id;
        this.company_id=company_id;
        this.date=date;
        this.confirmerId=confirmerId;
        this.company_name=company_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCompany_id() {
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

    public Integer getConfirmerId() {
        return confirmerId;
    }

    public void setConfirmerId(int confirmerId) {
        this.confirmerId = confirmerId;
    }
    
    public String getCompany_name() {
        return company_name;
    }
    
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

}
