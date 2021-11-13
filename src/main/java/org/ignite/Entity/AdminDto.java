package org.ignite.Entity;

import org.ignite.Entity.Admin;

public class AdminDto {
    private int admin_id;
    private String username;
  //  private String password;
    private String registered_date;

    public AdminDto() {

    }

    public AdminDto(Integer key, Admin value) {
        this.admin_id = key;
        this.username = value.getUsername();
       // this.password = value.getPassword();
        this.registered_date = value.getRegistered_date();
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public String getUsername() {
        return username;
    }

//    public String getPassword() {
//        return password;
//    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }
}
