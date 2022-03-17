package DataHandlers;

public class ConfirmerHandler {
    private String name;
    private String email;
    private Integer pin;

    public ConfirmerHandler() {
    }

    public ConfirmerHandler( String name, String email,Integer pin) {
        this.name = name;
        this.email = email;
        this.pin=pin;

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

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }


}
