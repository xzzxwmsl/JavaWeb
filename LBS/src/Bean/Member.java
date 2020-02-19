package Bean;

import java.sql.Timestamp;

public class Member {
    //  表示用户的bean对象
    private String username;
    private String type; // admin or user
    private String email;
    private String address;
    private String company;
    private String phone;
    private String name;
    private Timestamp register_time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegisterTime() {
        return register_time;
    }

    public void setRegisterTime(Timestamp register_time) {
        this.register_time = register_time;
    }

}
