package Models;

public class Wallet {
    private Integer wallet_id;
    private Integer balance ;
    private Integer user_id;
    private Integer company_id;
    private Integer district_id;
    private Integer admins_id;
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getAdmins_id() {
        return admins_id;
    }

    public void setAdmins_id(Integer admins_id) {
        this.admins_id = admins_id;
    }

    public void Wallet(Integer wallet_id, Integer balance, Integer user_id){
        this.wallet_id=wallet_id;
        this.balance=balance;
        this.user_id=user_id;
    }
    public Integer getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Integer wallet_id) {
        this.wallet_id = wallet_id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
