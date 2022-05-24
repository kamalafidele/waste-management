package Models;

public class District {
    private Long id;
    private String name;
    private String email;
    private Long walletId;

    public District() {
    }

    public District(Long id, String name, String email,Long walletId) {
        this.id = id;
        this.name = name;
        this.email = email;
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


    public void setWalletId(Long walletId){
        this.walletId = walletId;
    }

    public Long getWalletId(){
        return walletId;
    }

}
