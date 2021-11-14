package org.ignite.Entity;


public class UserDto {
    private  int id;
    private  String name;
    private String email;
    private String phone;
    private String avatar;
    private String registered_date;
    private int admin_id;

    public UserDto() {

    }

    public UserDto(UserKey key, User value) {
        this.id = key.getUserID();
        this.name = value.getUsername();
        this.email = value.getEmail();
        this.phone = value.getPhone();
        this.avatar = value.getAvatar();
        this.registered_date = value.getRegistered_date();
        this.admin_id = key.getAdminID();
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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }
}
