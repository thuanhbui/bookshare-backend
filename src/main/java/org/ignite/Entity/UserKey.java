package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;
import java.util.Objects;

public class UserKey implements Serializable {
    private int userId;
    @AffinityKeyMapped
    private int ADMINID;

    public UserKey(int userID, int AdminID) {
        this.userId = userID;
        this.ADMINID = AdminID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getADMINID() {
        return ADMINID;
    }

    public void setADMINID(int ADMINID) {
        this.ADMINID = ADMINID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserKey key = (UserKey)o;
        return userId == key.userId && ADMINID == key.ADMINID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, ADMINID);
    }

    @Override
    public String toString() {
        return "UserKey{" +
                "userId=" + userId +
                ", ADMINID=" + ADMINID +
                '}';
    }
}
