package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.User;
import org.ignite.Entity.UserKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.sql.Date;
import java.util.List;

@Repository
@RepositoryConfig(cacheName = "user")
public interface UserRepository extends IgniteRepository<User, UserKey> {
    @Query("SELECT * FROM USER")
    public List<Cache.Entry<UserKey, User>> getListUsers();

    public List<Cache.Entry<UserKey, User>> findByUsername(String name);

    @Query("SELECT * FROM USER WHERE userId = ?")
    public Cache.Entry<UserKey, User> findById(Integer user_id);

    @Query("DELETE FROM User WHERE userId = ?")
    public void deleteByUserId(Integer key);


}
