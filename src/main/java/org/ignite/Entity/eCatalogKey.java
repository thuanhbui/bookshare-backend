package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import java.io.Serializable;
import java.util.Objects;


public class eCatalogKey implements Serializable {
    private int CATALOG_ID;

    @AffinityKeyMapped
    private int ADMIN_ID;

    public eCatalogKey() {

    }

    public eCatalogKey(int catalogID, int adminID) {
        this.CATALOG_ID = catalogID;
        this.ADMIN_ID = adminID;
    }

    public int getCATALOG_ID() {
        return CATALOG_ID;
    }

    public int getADMIN_ID() {
        return ADMIN_ID;
    }

    public void setCATALOG_ID(int CATALOG_ID) {
        this.CATALOG_ID = CATALOG_ID;
    }

    public void setADMIN_ID(int ADMIN_ID) {
        this.ADMIN_ID = ADMIN_ID;
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
        return CATALOG_ID == key.CATALOG_ID && ADMIN_ID == key.ADMIN_ID;

    }

    @Override
    public int hashCode() {
        return Objects.hash(CATALOG_ID, ADMIN_ID);
    }

    @Override
    public String toString() {
        return "eCatalogKey{" +
                "CATALOG_ID=" + CATALOG_ID +
                ", ADMIN_ID=" + ADMIN_ID +
                '}';
    }
}
