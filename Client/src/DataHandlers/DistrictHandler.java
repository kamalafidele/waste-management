package DataHandlers;

public class DistrictHandler {
    private int  districtId;
    private String districtToken;
    private String districtName;
    private String password;

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
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

    public DistrictHandler() {}

    public DistrictHandler(int districtId, String districtToken, String districtName, String password) {
        this.districtId = districtId;
        this.districtToken = districtToken;
        this.districtName = districtName;
        this.password = password;

    }

}
