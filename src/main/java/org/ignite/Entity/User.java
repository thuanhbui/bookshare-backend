package org.ignite.Entity;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class User {

    private String username;
    private String password;
    private String email;
    private String phone;
    private MultipartFile avatarMulti;
    private String avatar;
    private Date registeredDate;
    private String role;

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

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public MultipartFile getAvatarMulti() {
        return avatarMulti;
    }

    public void setAvatarMulti(MultipartFile avatarMulti) {
        this.avatarMulti = avatarMulti;
    }


}
