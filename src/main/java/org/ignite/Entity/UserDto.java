package org.ignite.Entity;


import java.sql.Date;

public class UserDto {
    private  int id;
    private  String username;
    private String email;
    private String phone;
    private String avatar;
    private Date registeredDate;
    private int adminId;

    public UserDto() {

    }

    public UserDto(UserKey key, User value) {
        this.id = key.getUserId();
        this.username = value.getUsername();
        this.email = value.getEmail();
        this.phone = value.getPhone();
        this.avatar = value.getAvatar();
        this.registeredDate = value.getRegisteredDate();
        this.adminId = key.getADMINID();
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
