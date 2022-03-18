package DataHandlers;

public class ConfirmerHandler {
    private  String email;
    private String phone;
    private int role;
    private int id;
    private String username;
    private int password;

    public ConfirmerHandler(){}

    public ConfirmerHandler(int id, String username, String email,String phone,int password,int role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuserName() {
        return username;
    }

    public void setuserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone ){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    public void setRole(int role){
        this.role = role;
    }

    public int getRole(){
        return role;
    }

}
