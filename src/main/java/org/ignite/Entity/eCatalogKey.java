package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import java.io.Serializable;
import java.util.Objects;


public class eCatalogKey implements Serializable {
    private int CATALOGID;

    @AffinityKeyMapped
    private int ADMINID;

    public eCatalogKey() {

    }

    public eCatalogKey(int catalogID, int adminID) {
        this.CATALOGID = catalogID;
        this.ADMINID = adminID;
    }

    public int getCATALOGID() {
        return CATALOGID;
    }

    public void setCATALOGID(int CATALOGID) {
        this.CATALOGID = CATALOGID;
    }

    public int getADMINID() {
        return ADMINID;
    }

    public void setADMINID(int ADMINID) {
        this.ADMINID = ADMINID;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        eCatalogKey key = (eCatalogKey) object;
        return CATALOGID == key.CATALOGID && ADMINID == key.ADMINID;

    }

    @Override
    public int hashCode() {
        return Objects.hash(CATALOGID, ADMINID);
    }

    @Override
    public String toString() {
        return "eCatalogKey{" +
                "CATALOGID=" + CATALOGID +
                ", ADMINID=" + ADMINID +
                '}';
    }
}
