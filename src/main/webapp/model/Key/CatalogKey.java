package model.Key;

import java.util.Objects;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class CatalogKey {
   private int catalog_id;

   @AffinityKeyMapped
   private int ADMIN_ID;

   public CatalogKey(int catalogID, int adminID) {
      this.catalog_id = catalogID;
      this.ADMIN_ID = adminID;
   }

   public int geteCatalogId() {
      return catalog_id;
   }

   public int geteBookID() {
      return ADMIN_ID;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }
      if (object == null || getClass() != object.getClass()) {
         return false;
      }

      CatalogKey key = (CatalogKey) object;
      return catalog_id == key.catalog_id && ADMIN_ID == key.ADMIN_ID;

   }

   @Override
   public int hashCode() {
      return Objects.hash(catalog_id, ADMIN_ID);
   }

   @Override
   public String toString() {
      return "CatalogKey{" + "catalog_id=" + catalog_id + ", admin_id=" + ADMIN_ID + "\'}";
   }

}