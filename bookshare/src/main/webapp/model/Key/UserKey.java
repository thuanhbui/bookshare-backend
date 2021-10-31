import java.util.Objects;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class UserKey {
    private int userID;
    @AffinityKeyMapped
    private int AdminID;

    public UserKey(int userID, int AdminID) {
        this.userID = userID;
        this.AdminID = AdminID;
    }

    public int getUserID() {
        return userID;
    }

    public int getAdminID() {
        return AdminID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CityKey key = (CityKey)o;
        return userID == key.userID && AdminID == key.AdminID;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(userID, AdminID);
    }

    @Override 
    public String toString() {
        return "UserKey{" +
            "userID=" + userID +
            ", AdminID=" + AdminID +
            '}';
    }

}
