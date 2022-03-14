package Models;

import java.util.Date;

public class ServiceConfirmation {
    private int id;
    private int serviceId;
    private int companyId;
    private Date date;
    private int Confirmer;


    public ServiceConfirmation() {

    }

    public ServiceConfirmation(int id,int serviceId,int companyId,Date date,int Confirmer) {
        this.id = id;
        this.serviceId=serviceId;
        this.companyId = companyId;
        this.date=date;
        this.Confirmer=Confirmer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConfirmer() {
        return Confirmer;
    }

    public void setConfirmer(int confirmer) {
        Confirmer = confirmer;
    }

}