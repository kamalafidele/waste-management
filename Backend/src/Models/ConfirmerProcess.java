package Models;

public class ConfirmerProcess {
    private int id;
    private String Village;
    private String Company;
    private String HouseCode;


    public ConfirmerProcess() {

    }

    public ConfirmerProcess(int id, String Village, String Company, String HouseCode) {
        this.id = id;
        this.Village = Village;
        this.Company = Company;
        this.HouseCode = HouseCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getHouseCode() {
        return HouseCode;
    }

    public void setHouseCode(String houseCode) {
        HouseCode = houseCode;
    }

}