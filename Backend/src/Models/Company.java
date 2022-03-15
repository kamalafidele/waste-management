package Models;

public class Company {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Long pin;
    private int role;
    private int walletId;
    private int location;

    public Company() {
    }

    public Company(int id, String name, String email,String phone,Long pin, int role, int walletId, int location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone; 
        this.pin = pin;
        this.role = role;
        this.walletId = walletId;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    } 
    
    public void setRole(int role){
        this.role = role;
    }

    public int getRole(){
        return role;
    }

    public void setWalletId(int walletId){
        this.walletId = walletId;
    }

    public int getWalletId(){
        return walletId;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getLocation(){
        return location;
    }
}