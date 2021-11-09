package org.ignite.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private  int id;
    private  String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private String registered_date;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "password='" + password + '\'' +
                "email='" + email + '\'' +
                "phone='" + phone + '\'' +
                "avatar='" + avatar + '\'' +
                "registered_date='" + registered_date + "\'}";
    }

}
