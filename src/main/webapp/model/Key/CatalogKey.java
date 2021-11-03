package model.Key;


import java.util.Objects;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class CatalogKey {
   private int catalog_id;

   @AffinityKeyMapped
   private int BOOK_ID;

   public CatalogKey(int CatalogID, int bookID) {
      this.catalog_id = CatalogID;
      this.BOOK_ID = bookID;
   }

   public int getCatalogId() {
      return catalog_id;
   }

   public int getAdminID() {
      return BOOK_ID;
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
      return catalog_id == key.catalog_id && BOOK_ID == key.BOOK_ID;

   }

   @Override
   public int hashCode() {
      return Objects.hash(catalog_id, BOOK_ID);
   }

   @Override
   public String toString() {
      return "CityKey{" +
            "catalog_id=" + catalog_id + 
            ", BOOK_ID=" + BOOK_ID + "}";
   }

}