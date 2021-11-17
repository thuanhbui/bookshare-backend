package org.ignite.Entity;

import java.io.Serializable;
import java.sql.Date;


public class Admin implements Serializable {

   // private int adminId;

    private String username;

    private String password;

    private Date registeredDate;

    public Admin() {

    }

    public Admin(String username, String password, Date registeredDate) {
        this.username = username;
        this.password = password;
        this.registeredDate = registeredDate;
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

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
