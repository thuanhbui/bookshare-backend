package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RepositoryConfig(cacheName = "admin")
public interface AdminRepository extends IgniteRepository<Admin, Integer> {

    @Query("SELECT * FROM ADMIN")
    List<List<?>> getListAdmin();



}
