package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Admin;
import org.ignite.Entity.eBook;
import org.ignite.Entity.eBookKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;


@Repository
@RepositoryConfig(cacheName = "eBook")
public interface BookRepository extends IgniteRepository<eBook, eBookKey> {

    @Query("SELECT * FROM EBOOK")
    List<Cache.Entry<eBookKey, eBook>> getListBooks();

    public List<Cache.Entry<eBookKey, eBook>> findByTitle(String title);

    @Query("SELECT * FROM EBOOK WHERE eBookId = ?")
    public Cache.Entry<eBookKey, eBook> findById(String id);

    @Query("DELETE FROM eBook WHERE bookId = ?")
    void deleteById(String bookId);

    @Query("SELECT * FROM eBook " +
            "WHERE lastUpdate < (SELECT MAX(lastUpdate) FROM eBook) " +
            "OR lastUpdate = (SELECT MAX(lastUpdate) FROM eBook) " +
            "ORDER BY lastUpdate DESC LIMIT 5")
    public List<Cache.Entry<eBookKey, eBook>> getListNewBooks();


}
