package DataHandlers;

public class LoginData {
    private String districtToken;
    private String password;

    public LoginData() {
    }

    public LoginData(String districtToken, String password) {
        this.districtToken = districtToken;
        this.password = password;
    }

    public String getDistrictToken() {
        return this.districtToken;
    }

    public void setDistrictToken(String districtToken) {
        this.districtToken = districtToken;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
