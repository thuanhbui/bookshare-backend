package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;
import java.util.Objects;

public class eBookKey implements Serializable {
    private String eBookId;

    @AffinityKeyMapped
    private int USERID;


    private int CATALOGID;

    public eBookKey(String eBookID, int userID, int catalogID) {
        this.eBookId = eBookID;
        this.USERID = userID;
        this.CATALOGID = catalogID;
    }

    public String geteBookId() {
        return eBookId;
    }

    public void seteBookId(String eBookId) {
        this.eBookId = eBookId;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public int getCATALOGID() {
        return CATALOGID;
    }

    public void setCATALOG_ID(int CATALOG_ID) {
        this.CATALOGID = CATALOG_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        eBookKey key = (eBookKey) o;
        return eBookId.equals(key.eBookId) && USERID == key.USERID && CATALOGID == key.CATALOGID;
    }




    @Override
    public int hashCode() {
        return Objects.hash(eBookId, USERID, CATALOGID);
    }

    @Override
    public String toString() {
        return "eBookKey{" +
                "eBookId='" + eBookId + '\'' +
                ", USERID=" + USERID +
                ", CATALOGID=" + CATALOGID +
                '}';
    }
}
