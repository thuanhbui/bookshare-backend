
import java.util.Objects;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class CatalogKey {
   private int CatalogID;

   @AffinityKeyMapped
   private int AdminID;

   public CatalogKey(int CatalogID, int AdminID) {
      this.CatalogID = CatalogID;
      this.AdminID = AdminID;
   }

   public int getCatalogId() {
      return CatalogID;
   }

   public int getAdminID() {
      return AdminID;
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
      return catalogID == key.CatalogID && AdminID == key.AdminID;

   }

   @Override
   public int hashCode() {
      return Objects.hash(CatalogID, AdminID);
   }

   @Override
   public String toString() {
      return "CityKey{" +
            "CatalogID=" + CatalogID + 
            ", AdminID=" + AdminID + "}";
   }

}
