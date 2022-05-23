package Models;

public class Company {
    private Long id;
    private String name;
    private String email;
    private Long tin;
    private Long walletId;

    public Company() {
    }

    public Company(Long id, String name, String email,Long tin, Long walletId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tin = tin;
        this.walletId = walletId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getTin() {
        return tin;
    }

    public void setTin(Long tin) {
        this.tin = tin;
    }

    public void setWalletId(Long walletId){
        this.walletId = walletId;
    }

    public Long getWalletId(){
        return walletId;
    }
}