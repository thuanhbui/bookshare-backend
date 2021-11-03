package model.Entity;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String registered_date;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredDate() {
        return registered_date;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registered_date = registeredDate;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            "password='" + password + '\'' +
            "email='" + email + '\'' +
            "phone='" + phone + '\'' +
            "address='" + address + '\'' +
            "registered_date='" + registered_date + "\'}";
    }

}
