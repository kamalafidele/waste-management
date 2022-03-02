package DataHandlers;

public class ConfirmationProcessHandler {

    private String HouseCode;
    private long id;
    private long conformerId;
    private String village;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getHouseCode() {
        return HouseCode;
    }
    public void setHouseCode(String houseCode) {
        HouseCode = houseCode;
    }
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public long getConformerId() {
        return conformerId;
    }
    public void setConformerId(long conformerId) {
        this.conformerId = conformerId;
    }
}