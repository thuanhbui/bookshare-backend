package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;
import java.util.Objects;

public class eBookKey implements Serializable {
    private String eBook_id;

    @AffinityKeyMapped
    private int USER_ID;

    public eBookKey(String eBookID, int userID) {
        this.eBook_id = eBookID;
        this.USER_ID = userID;
    }

    public String geteBookID() {
        return eBook_id;
    }

    public int getUserID() {
        return USER_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        eBookKey key = (eBookKey) o;
        return eBook_id.equals(key.eBook_id) && USER_ID == key.USER_ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eBook_id, USER_ID);
    }

    @Override
    public String toString() {
        return "eBookKey{" + "eBook_id='" + eBook_id + '\'' + ", USER_ID='" + USER_ID + '}';
    }
}
