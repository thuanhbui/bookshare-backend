package org.ignite.Entity;


public class Admin {

    private String username;
    private String password;
    private String registered_date;

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

    public String getRegisteredDate() {
        return registered_date;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registered_date = registeredDate;
    }

//    @Override
//    public String toString() {
//        return "Admin [" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", registered_date='" + registered_date + "\'";
//    }
}
