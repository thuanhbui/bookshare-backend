package org.ignite.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private  int id;
    @QuerySqlField
    private  String username;
    @QuerySqlField
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private String registered_date;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "password='" + password + '\'' +
                "email='" + email + '\'' +
                "phone='" + phone + '\'' +
                "avatar='" + avatar + '\'' +
                "registered_date='" + registered_date + "\'}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

}
