package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;
import java.util.Objects;

public class eBookKey implements Serializable {
    private String eBook_id;

    @AffinityKeyMapped
    private int USER_ID;


    private int CATALOG_ID;

    public eBookKey(String eBookID, int userID, int catalogID) {
        this.eBook_id = eBookID;
        this.USER_ID = userID;
        this.CATALOG_ID = catalogID;
    }

    public String geteBook_id() {
        return eBook_id;
    }

    public void seteBook_id(String eBook_id) {
        this.eBook_id = eBook_id;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getCATALOG_ID() {
        return CATALOG_ID;
    }

    public void setCATALOG_ID(int CATALOG_ID) {
        this.CATALOG_ID = CATALOG_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        eBookKey key = (eBookKey) o;
        return eBook_id.equals(key.eBook_id) && USER_ID == key.USER_ID && CATALOG_ID == key.CATALOG_ID;
    }




    @Override
    public int hashCode() {
        return Objects.hash(eBook_id, USER_ID, CATALOG_ID);
    }

    @Override
    public String toString() {
        return "eBookKey{" +
                "eBook_id='" + eBook_id + '\'' +
                ", USER_ID=" + USER_ID +
                ", CATALOG_ID=" + CATALOG_ID +
                '}';
    }
}
