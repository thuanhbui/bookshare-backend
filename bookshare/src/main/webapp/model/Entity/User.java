public class User {
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String registeredDate;
    
    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
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
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + userName + '\'' +
            "password='" + password + '\'' +
            "email='" + email + '\'' +
            "phone='" + phone + '\'' +
            "address='" + address + '\'' +
            "registeredDate='" + registeredDate + "}";
    }

}
