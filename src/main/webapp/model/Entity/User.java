package model.Entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String registered_date;

    public User(String username, String password, String email, String phone, String address, String registered_date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registered_date = registered_date;
    }

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
