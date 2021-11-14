package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.User;
import org.ignite.Entity.UserKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryConfig(cacheName = "user1")
public interface UserRepository extends IgniteRepository<User, UserKey> {
    @Query("SELECT * FROM USER")
    public List<Cache.Entry<UserKey, User>> getListUsers();

    public List<Cache.Entry<UserKey, User>> findByUserName(String name);

    @Override
    Optional<User> findById(UserKey userKey);

    @Query("SELECT * FROM USER WHERE user_id = ?")
    public Cache.Entry<UserKey, User> findById(Integer user_id);

    void deleteById(UserKey key);
}
