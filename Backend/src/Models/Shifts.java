package Models;

import java.util.Date;

public class Shifts {
    private int id;
    private int company_id;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConfirmerId() {
        return confirmerId;
    }

    public void setConfirmerId(int confirmerId) {
        this.confirmerId = confirmerId;
    }

    private int confirmerId;
}
