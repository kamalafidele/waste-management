package Models;

import java.util.Date;

public class ServiceConfirmation {
    private int id;
    private int serviceId;
    private int shiftId;
    private int ConfirmerId;


    public ServiceConfirmation() {
    }

    public ServiceConfirmation(int id,int serviceId,int shiftId,Date date,int Confirmer) {
        this.id = id;
        this.serviceId=serviceId;
        this.shiftId=shiftId;
        this.ConfirmerId=Confirmer;
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

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getConfirmerId() {
        return ConfirmerId;
    }

    public void setConfirmerId(int confirmerId) {
        ConfirmerId = confirmerId;
    }

}