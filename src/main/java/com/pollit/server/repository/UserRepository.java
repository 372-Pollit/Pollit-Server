package com.pollit.server.repository;


import com.pollit.server.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from \"user\"\n" +
                    "where username = :username and \"password\" = :password")
    public User isUser(@Param("username") String username, @Param("password") String password );

}
