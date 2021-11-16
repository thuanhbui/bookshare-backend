package org.ignite.Entity;


public class UserDto {
    private  int id;
    private  String name;
    private String email;
    private String phone;
    private String avatar;
    private String registeredDate;
    private int adminId;

    public UserDto() {

    }

    public UserDto(UserKey key, User value) {
        this.id = key.getUserId();
        this.name = value.getUsername();
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
