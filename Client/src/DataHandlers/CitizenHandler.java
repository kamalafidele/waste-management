package DataHandlers;

public class CitizenHandler {
    private String name;
    private String email;
    private String phone;
    private int role;
    private int location;

    public CitizenHandler() {
    }

    public CitizenHandler( String name, String email,String phone, int role, int location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRole(int role){
        this.role = role;
    }

    public int getRole(){
        return role;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getLocation(){
        return location;
    }
}