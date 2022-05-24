package Models;

import java.util.Date;

public class ServiceConfirmation {
    private  Integer id;
    private Integer serviceId;
    private Integer shiftId;
    private Integer ConfirmerId;


    public ServiceConfirmation() {
    }

    public ServiceConfirmation(Integer id,Integer serviceId,Integer shiftId,Integer Confirmer) {
        this.id = id;
        this.serviceId=serviceId;
        this.shiftId=shiftId;
        this.ConfirmerId=Confirmer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getConfirmerId() {
        return ConfirmerId;
    }

    public void setConfirmerId(int confirmerId) {
        ConfirmerId = confirmerId;
    }

}