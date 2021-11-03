package model.Key;

import java.util.Objects;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class BookKey {
    private int book_id;

    @AffinityKeyMapped
    private int USER_ID;

    public BookKey(int bookID, int userID) {
        this.book_id = bookID;
        this.USER_ID = userID;
    }

    public int getBookID() {
        return book_id;
    }


    public int getUserKey() {
        return USER_ID;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BookKey key = (BookKey)o;
        return book_id == key.book_id && USER_ID == key.USER_ID;
    }

    @Override public int hashCode() {
        return Objects.hash(book_id, USER_ID);
    }

    @Override public String toString() {
        return "BookKey{" +
            "book_id=" + book_id +
            ", USER_ID='" + USER_ID + 
            '}';
    }
}
