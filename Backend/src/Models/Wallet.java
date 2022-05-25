package Models;

import java.security.Timestamp;

public class Wallet {
    private Integer wallet_id;
    private Integer balance ;
    private Integer user_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private int status;
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public void Wallet(Integer wallet_id, Integer balance, Integer user_id,Timestamp created_at,Timestamp updated_at, int status){
        this.wallet_id=wallet_id;
        this.balance=balance;
        this.user_id=user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
    }
    public Integer getWallet_id() {
        return wallet_id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
