package Models;

public class District {
    private String districtId;
    private String username;
    private String password;

    public District(){}

    public District(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getdistrictToken() {
        return username;
    }

    public void setdistrictToken(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
