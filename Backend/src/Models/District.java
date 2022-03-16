package Models;

public class District {
    private String districtToken;
    private String districtName;
    private String password;

    public District(){}

    public District(String districtToken, String districtName,String password){
        this.setDistrictToken(districtToken);
        this.setDistrictName(districtName);
        this.setPassword(password);
    }

    public String getDistrictToken() {
        return districtToken;
    }

    public void setDistrictToken(String districtToken) {
        this.districtToken = districtToken;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
