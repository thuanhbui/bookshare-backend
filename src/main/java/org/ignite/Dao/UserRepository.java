package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.User;
import org.ignite.Entity.UserKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryConfig(cacheName = "user1")
public interface UserRepository extends IgniteRepository<User, UserKey> {
    @Query("SELECT user_id, username, email, phone, avatar, registered_date, admin_id FROM USER")
    public List<?> getListUsers();

    @Query("SELECT user_id, username, email, phone, avatar, registered_date, admin_id FROM USER WHERE name_catalog = ? ")
    public List<?> searchUser(String keyword);

    @Query("SELECT user_id, username, email, phone, avatar, registered_date, admin_id FROM USERWHERE catalog_id = ?")
    public List<?> findUserById(int catalog_id);
}
