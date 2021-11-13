package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import java.io.Serializable;
import java.util.Objects;


public class eCatalogKey implements Serializable {
    private int CATALOG_ID;

    @AffinityKeyMapped
    private String EBOOK_ID;

    public eCatalogKey() {

    }

    public eCatalogKey(int catalogID, String bookID) {
        this.CATALOG_ID = catalogID;
        this.EBOOK_ID = bookID;
    }

    public int geteCatalogId() {
        return CATALOG_ID;
    }

    public String geteBookID() {
        return EBOOK_ID;
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
        return CATALOG_ID == key.CATALOG_ID && EBOOK_ID.equals(key.EBOOK_ID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(CATALOG_ID, EBOOK_ID);
    }

    @Override
    public String toString() {
        return "eCatalogKey{" +
                "CATALOG_ID=" + CATALOG_ID +
                ", EBOOK_ID='" + EBOOK_ID + '\'' +
                '}';
    }
}
