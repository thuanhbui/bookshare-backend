package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Admin;
import org.ignite.Entity.eCatalog;
import org.ignite.Entity.eCatalogKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;


@Repository
@RepositoryConfig(cacheName = "admin1")
public interface AdminRepository extends IgniteRepository<Admin, Integer> {

    @Query("SELECT admin_id, username, password, registered_date FROM ADMIN")
    List<?> getListAdmins();

    @Query("SELECT admin_id, username, password, registered_date FROM Admin WHERE admin_id = ?")
    public List<?> findAdminById(int admin_id);


}
