package org.ignite.Entity;

import java.io.Serializable;

public class Like implements Serializable {
    private Integer userId;
    private String eBookId;

    public Like(Integer userId, String eBookId) {
        this.userId = userId;
        this.eBookId = eBookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String geteBookId() {
        return eBookId;
    }

    public void seteBookId(String eBookId) {
        this.eBookId = eBookId;
    }
}
