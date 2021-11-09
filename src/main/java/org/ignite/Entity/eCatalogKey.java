package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.util.Objects;

public class eCatalogKey {
    private int catalog_id;

    @AffinityKeyMapped
    private String EBOOK_ID;

    public eCatalogKey(int catalogID, String bookID) {
        this.catalog_id = catalogID;
        this.EBOOK_ID = bookID;
    }

    public int geteCatalogId() {
        return catalog_id;
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
        return catalog_id == key.catalog_id && EBOOK_ID.equals(key.EBOOK_ID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog_id, EBOOK_ID);
    }

    @Override
    public String toString() {
        return "CatalogKey{" + "catalog_id=" + catalog_id + ", EBOOK_ID='" + EBOOK_ID + "\'}";
    }
}
