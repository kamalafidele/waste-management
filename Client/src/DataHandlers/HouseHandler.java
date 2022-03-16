package DataHandlers;

import java.math.BigInteger;

public class HouseHandler {
    private int id;
    private String name;
    private BigInteger pin;
    private String email;
    private String phone;
    private int role;
    private int wallet;
    private int location;

    private String message;

    public HouseHandler() {}

    public HouseHandler(int id, String name, BigInteger pin, String email, String phone, int role, int wallet, int location, String message) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.wallet = wallet;
        this.location = location;
        this.message = message;
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

    public BigInteger getPin() {
        return pin;
    }

    public void setPin(BigInteger pin) {
        this.pin = pin;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String genPin() {
        int count = 0;
        String ALPHA_NUMERIC_STRING = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count < 5) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            count++;
        }
        return builder.toString();
    }
}
