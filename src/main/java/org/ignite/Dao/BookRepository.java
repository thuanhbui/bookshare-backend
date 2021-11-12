package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.eBook;
import org.ignite.Entity.eBookKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryConfig(cacheName = "eBook1")
public interface BookRepository extends IgniteRepository<eBook, eBookKey> {
    @Query("SELECT eBook_id, title, description, image_link, file_link, language, " +
            "release_year, last_update, viewers, user_id FROM EBOOK")
    public List<?> getListBooks();

    @Query("SELECT eBook_id, title, description, image_link, file_link, language, " +
            "release_year, last_update, viewers, user_id FROM EBOOK WHERE title = ? ")
    public List<List<?>> searchBook(String keyword);

    @Query("SELECT eBook_id, title, description, image_link, file_link, language, " +
            "release_year, last_update, viewers, user_id FROM EBOOK WHERE eBook_id = ?")
    public List<?> findBookById(String catalog_id);
}
