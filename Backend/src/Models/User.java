package Models;

public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Long role;
    private Long wallet;
    private Long location;
    private Long work_at;

    public User() {
    }

    public User(Long id, String name, String email, String phone, String password, Long role, Long wallet, Long location, Long work_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.wallet = wallet;
        this.location = location;
        this.work_at = work_at;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getWallet() {
        return wallet;
    }

    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public Long getWork_at() {
        return work_at;
    }

    public void setWork_at(Long work_at) {
        this.work_at = work_at;
    }
}
