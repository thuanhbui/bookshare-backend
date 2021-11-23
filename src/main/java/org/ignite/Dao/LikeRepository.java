package org.ignite.Dao;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Like;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;


@Repository
@RepositoryConfig(cacheName = "likes")
public interface LikeRepository extends IgniteRepository<Like, Integer> {

    @Query("SELECT * FROM likes WHERE userId = ?")
    public List<Cache.Entry<Integer, Like>> findByUserId(Integer userId);

    @Query("SELECT * FROM likes WHERE eBookId = ?")
    public List<Cache.Entry<Integer, Like>> findByEBookId(String eBookId);

//    @Query("SELECT eBook.* FROM eBook INNER JOIN likes ON eBook.eBookId = likes.eBookId "
//        + "INNER JOIN user ON likes.userId = user.userId WHERE user.userId = ?")
//    public List<Cache.Entry>

}
