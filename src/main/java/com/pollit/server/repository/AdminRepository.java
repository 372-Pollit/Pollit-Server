package com.pollit.server.repository;

import com.pollit.server.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from admin \n" +
                    "where user_id = :id")
    public Admin isAdmin(@Param("id") int id);

}
