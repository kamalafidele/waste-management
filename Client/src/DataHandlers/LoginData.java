package DataHandlers;

public class LoginData {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Long pin;
    private int role;
    private int walletId;
    private int location;

    public LoginData() {
    }

    public LoginData(  String email,Long pin) {

        this.email = email;

        this.pin = pin;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }



}
