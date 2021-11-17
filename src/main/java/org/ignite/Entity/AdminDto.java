package org.ignite.Entity;

import org.ignite.Entity.Admin;

import java.sql.Date;

public class AdminDto {
    private int adminId;
    private String username;
  //  private String password;
    private Date registeredDate;

    public AdminDto() {

    }

    public AdminDto(Integer key, Admin value) {
        this.adminId = key;
        this.username = value.getUsername();
       // this.password = value.getPassword();
        this.registeredDate = value.getRegisteredDate();
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
