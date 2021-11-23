package org.ignite.Dao;


import org.apache.ignite.IgniteCache;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Admin;
import org.ignite.Entity.eBook;
import org.ignite.Entity.eBookKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;
import java.util.Optional;


@Repository
@RepositoryConfig(cacheName = "eBook")
public interface BookRepository extends IgniteRepository<eBook, eBookKey> {

    @Query("SELECT * FROM EBOOK")
    public List<Cache.Entry<eBookKey, eBook>> getListBooks();

    public List<Cache.Entry<eBookKey, eBook>> findByTitle(String title);

    @Query("SELECT * FROM EBOOK WHERE eBookId = ?")
    public Cache.Entry<eBookKey, eBook> findById(String id);

    @Query("DELETE FROM eBook WHERE eBookId = ?")
    public void deleteByBookId(String bookId);

    @Query("SELECT * FROM eBook " +
            "WHERE lastUpdate < (SELECT MAX(lastUpdate) FROM eBook) " +
            "OR lastUpdate = (SELECT MAX(lastUpdate) FROM eBook) AND title LIKE ? " +
            "ORDER BY lastUpdate DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getListNewBooks(String keyWord);

    @Query("SELECT * FROM eBook " +
            "WHERE lastUpdate < (SELECT MAX(lastUpdate) FROM eBook) " +
            "OR lastUpdate = (SELECT MAX(lastUpdate) FROM eBook) AND userId = ? AND title LIKE ? " +
            "ORDER BY lastUpdate DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getListNewBooksUser(Integer userId, String keyWord);

    @Query("SELECT * FROM eBook " +
            "WHERE lastUpdate < (SELECT MAX(lastUpdate) FROM eBook) " +
            "OR lastUpdate = (SELECT MAX(lastUpdate) FROM eBook) AND catalogId = ? AND title LIKE ? " +
            "ORDER BY lastUpdate DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getListNewBooksCata(Integer catalogId, String keyWord);

    @Query("SELECT * FROM eBook " +
            "WHERE lastUpdate < (SELECT MAX(lastUpdate) FROM eBook) " +
            "OR lastUpdate = (SELECT MAX(lastUpdate) FROM eBook) AND userId = ? AND catalogId = ? AND title LIKE ? " +
            "ORDER BY lastUpdate DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getListNewBooksUSerCata(Integer userId, Integer cataID, String keyWord);

    @Query("SELECT * FROM eBook WHERE title LIKE ? ORDER BY likes DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getTop10(String keyWord);

    @Query("SELECT * FROM eBook WHERE catalogId = ? AND title LIKE ? ORDER BY likes DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getTop10Cata(Integer catalogId, String keyWord);

    @Query("SELECT * FROM eBook WHERE userId = ? AND title LIKE ? ORDER BY likes DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getTop10User(Integer userId, String keyWord);

    @Query("SELECT * FROM eBook WHERE catalogId = ? AND userId = ? AND title LIKE ? ORDER BY likes DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getTop10UserCata(Integer catalogId, Integer userId, String keyWord);

    @Query("SELECT * FROM eBook ORDER BY likes DESC;")
    public List<Cache.Entry<eBookKey, eBook>> getHotBooks();

    public List<Cache.Entry<eBookKey, eBook>> findByCatalogId(Integer catalogId);

    @Query("SELECT * FROM EBOOK WHERE userId = ?")
    public List<Cache.Entry<eBookKey, eBook>> findByUserId(Integer userId);

    @Override
    IgniteCache<eBookKey, eBook> cache();

    @Query("SELECT * FROM EBOOK WHERE title LIKE ? " )
    public List<Cache.Entry<eBookKey, eBook>> findByKeyWord(String keyWord);

    @Query("SELECT * FROM EBOOK WHERE title LIKE ? and userId = ? and catalogId = ?")
    public List<Cache.Entry<eBookKey, eBook>> findByKeyUserCata(String keyWord, Integer userId, Integer catalogId);

    @Query("SELECT * FROM EBOOK WHERE title LIKE ? and userId = ?")
    public List<Cache.Entry<eBookKey, eBook>> findByKeyUser(String keyWord, Integer userId);

    @Query("SELECT * FROM EBOOK WHERE title LIKE ?  and catalogId = ?")
    public List<Cache.Entry<eBookKey, eBook>> findByKeyCata(String keyWord, Integer catalogId);

    @Query("SELECT eBook.* FROM eBook INNER JOIN likes ON eBook.eBookId = likes.eBookId "
            + "INNER JOIN user ON likes.userId = user.userId WHERE user.userId = ? AND liked = 1")
    public List<Cache.Entry<eBookKey, eBook>> findByLike(Integer userId);

//    @Query("SELECT eBook.* FROM eBook INNER JOIN likes ON eBook.eBookId = likes.eBookId "
//            + "INNER JOIN user ON likes.userId = user.userId WHERE user.userId = ? AND liked = 1 "
//            + "AND eBook.title LIKE '")
//    public List<Cache.Entry<eBookKey, eBook>> findByLikeKey(Integer userId, String keyWord);

}
