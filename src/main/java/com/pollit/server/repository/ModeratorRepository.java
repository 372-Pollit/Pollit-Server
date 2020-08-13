package com.pollit.server.repository;


import com.pollit.server.model.Moderator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ModeratorRepository extends CrudRepository<Moderator, Integer> {

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from moderator\n" +
                    "where user_id = :id")
    public Moderator isModerator(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "delete\n" +
            "from moderator\n" +
            "where user_id = :id")
    public void remove(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "insert\n" +
                    "into moderator (\"user_id\")\n" +
                    "values (:id)")
    public void add(@Param("id") int id);

}
