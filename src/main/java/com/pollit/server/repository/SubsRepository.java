package com.pollit.server.repository;

import com.pollit.server.model.Category;
import com.pollit.server.model.Subs;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubsRepository extends CrudRepository<Subs, Integer> {

    @Query(value = "select s.category " +
            "from Subs s " +
            "where s.userId = :userId")
    List<Category> findSubbedCategories(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("delete " +
            "from Subs s " +
            "where s.userId = :userId and s.categoryId = :categoryId")
    void unSub(@Param("userId") int userId, @Param("categoryId") int categoryId);

    @Query("select c " +
            "from Category c " +
            "where c not in (" +
            "select s.categoryId " +
            "from Subs s where s.userId = :userId)")
    List<Category> findUnSubbedCategories(@Param("userId") int userId);
}
