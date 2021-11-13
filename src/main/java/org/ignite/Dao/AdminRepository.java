package org.ignite.Dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.Admin;


import org.springframework.stereotype.Repository;


import javax.cache.Cache;
import java.util.List;
import java.util.Optional;


@Repository
@RepositoryConfig(cacheName = "admin")
public interface AdminRepository extends IgniteRepository<Admin, Integer> {

    @Query("SELECT * FROM ADMIN")
    List<Cache.Entry<Integer, Admin>> getListAdmins();

    public List<Cache.Entry<Integer, Admin>> findByUsername(String name);

    @Query("SELECT * FROM Admin WHERE admin_id = ?")
    public Cache.Entry<Integer, Admin> findById(int id);


    void deleteById(Integer integer);


    //public Optional<Admin> findById(Integer id);



}
