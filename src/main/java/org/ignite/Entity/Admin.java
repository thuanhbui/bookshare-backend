package org.ignite.Entity;


import org.apache.ignite.cache.affinity.AffinityKey;


import java.io.Serializable;


public class Admin implements Serializable {



    private String username;

    private String password;

    private String registered_date;



    public Admin() {

    }


    public Admin(String username, String password, String registered_date) {

        this.username = username;
        this.password = password;
        this.registered_date = registered_date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registered_date='" + registered_date + '\'' +
                '}';
    }
}
