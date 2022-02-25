package Models;

public class Company {
    private Long id;
    private String name;
    private String email;
    private Long paymentCode;
    private Long password;

    public Company() {
    }

    public Company(Long id, String name, String email, Long paymentCode, Long password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.paymentCode = paymentCode;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(Long paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }
}
