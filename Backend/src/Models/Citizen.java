package Models;

public class Citizen {
    private String  fullName;
    private Long national_id;
    private Number phone_number;
    private String email;
    private String district;
    private String sector;
    private String cell;
    private String village;

    public Citizen() {

    }

    public Citizen(String fullName, Long national_id, Number phone_number, String email, String district, String sector, String cell, String village) {
        this.fullName = fullName;
        this.national_id = national_id;
        this.phone_number = phone_number;
        this.email = email;
        this.district = district;
        this.sector = sector;
        this.cell = cell;
        this.village = village;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getNational_id() {
        return national_id;
    }

    public void setNational_id(Long national_id) {
        this.national_id = national_id;
    }

    public Number getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Number phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
