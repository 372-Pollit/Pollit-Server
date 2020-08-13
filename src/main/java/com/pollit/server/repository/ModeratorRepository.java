package com.pollit.server.repository;


import com.pollit.server.model.Moderator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModeratorRepository extends CrudRepository<Moderator, Integer> {

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from moderator\n" +
                    "where user_id = :id")
    public Moderator isModerator(@Param("id") int id);

}
