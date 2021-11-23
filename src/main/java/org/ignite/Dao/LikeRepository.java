package org.ignite.Dao;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Like;
import org.ignite.Entity.LikeKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;


@Repository
@RepositoryConfig(cacheName = "likes")
public interface LikeRepository extends IgniteRepository<Like, LikeKey> {

    @Query("SELECT * FROM likes WHERE userId = ?")
    public List<Cache.Entry<LikeKey, Like>> findByUserId(Integer userId);

    @Query("SELECT * FROM likes WHERE eBookId = ?")
    public List<Cache.Entry<LikeKey, Like>> findByEBookId(String eBookId);



}
