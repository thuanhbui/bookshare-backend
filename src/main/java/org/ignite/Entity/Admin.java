package org.ignite.Entity;

import java.io.Serializable;


public class Admin implements Serializable {

    private int adminId;

    private String username;

    private String password;

    private String registeredDate;

    public Admin() {

    }

    public Admin(String username, String password, String registeredDate) {
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
