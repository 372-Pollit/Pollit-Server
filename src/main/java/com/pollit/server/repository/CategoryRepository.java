package com.pollit.server.repository;

import com.pollit.server.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query("delete\n" +
            "from Category\n" +
            "where id = :categoryId")
    void remove(@Param("categoryId") int categoryId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "insert\n" +
                    "into category (\"name\")\n" +
                    "values (:categoryName)")
    void add(@Param("categoryName") String categoryName);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update category\n" +
                    "set \"name\" = :categoryName\n" +
                    "where id = :categoryId")
    void update(@Param("categoryId") int categoryId, @Param("categoryName") String categoryName);

}
