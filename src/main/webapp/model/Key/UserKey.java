package model.Key;

import java.util.Objects;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class UserKey {
    private int user_id;
    @AffinityKeyMapped
    private int ADMIN_ID;

    public UserKey(int userID, int AdminID) {
        this.user_id = userID;
        this.ADMIN_ID = AdminID;
    }

    public int getUserID() {
        return user_id;
    }

    public int getAdminID() {
        return ADMIN_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserKey key = (UserKey)o;
        return user_id == key.user_id && ADMIN_ID == key.ADMIN_ID;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(user_id, ADMIN_ID);
    }

    @Override 
    public String toString() {
        return "UserKey{" +
            "user_id=" + user_id +
            ", ADMIN_ID=" + ADMIN_ID +
            '}';
    }

}
