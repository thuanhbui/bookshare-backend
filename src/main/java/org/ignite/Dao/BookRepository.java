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
            "OR lastUpdate = (SELECT MAX(lastUpdate) FROM eBook) " +
            "ORDER BY lastUpdate DESC LIMIT 5")
    public List<Cache.Entry<eBookKey, eBook>> getListNewBooks();

    @Query("SELECT * FROM eBook WHERE catalogId = ? ORDER BY likes DESC LIMIT 10;")
    public List<Cache.Entry<eBookKey, eBook>> getTop10(Integer catalogId);

    public List<Cache.Entry<eBookKey, eBook>> findByCatalogId(Integer catalogId);

    @Query("SELECT * FROM EBOOK WHERE userId = ?")
    public List<Cache.Entry<eBookKey, eBook>> findByUserId(Integer userId);

    @Override
    IgniteCache<eBookKey, eBook> cache();

    public Optional<eBook> findByImageLink(String imageLink);

    public Optional<eBook> findByFileLink(String imageLink);

}
