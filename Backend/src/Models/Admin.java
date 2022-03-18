package Models;

import Repositories.LocationRepo;

import java.sql.SQLException;
import java.util.Random;

public class Admin {
    private String name;
    private String email;
    private String phone;
    private long pin;
    private int role = 1;
    private String location;
    private long password;
    private LocationRepo locationRepo;

    public Admin(){}

    public Admin(String name, String password){
        this.setName(name);
        this.setPassword(password);
    }

    public Admin(String name, String email, String Phone, String Location) throws SQLException {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(Phone);
        this.setPin();
        this.setRole();
        this.setLocation(Location);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getPin() {
        return pin;
    }

    public void setPin() {
        Random random=new Random();
        long paymentCode = random.nextLong(500_000_000);
        this.pin = paymentCode;
    }

    public int getRole() {
        return role;
    }

    public void setRole() {
        this.role = 1;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws SQLException {
        locationRepo = new LocationRepo();
        int result  = locationRepo.getLocationId(location);

        if(result == -1){
            return;
        }

        this.location = location;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Long.parseLong(password);
    }
}
