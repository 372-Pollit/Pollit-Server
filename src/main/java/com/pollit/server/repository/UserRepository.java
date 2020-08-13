package com.pollit.server.repository;


import com.pollit.server.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from \"user\"\n" +
                    "where username = :username and \"password\" = :password")
    public User isUser(@Param("username") String username, @Param("password") String password);

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from \"user\"\n" +
                    "where lower(username) like %:searchString%\n" +
                    "union\n" +
                    "(\n" +
                    "   select *\n" +
                    "   from \"user\"\n" +
                    "   where lower(first_name) like %:searchString%\n" +
                    ")\n" +
                    "union\n" +
                    "(\n" +
                    "   select *\n" +
                    "   from \"user\"\n" +
                    "   where lower(last_name) like %:searchString%\n" +
                    ")\n" +
                    "limit 20\n" +
                    "offset :pageNumber\n")
    public List<User> search(@Param("searchString") String searchString, @Param("pageNumber") int pageNumber);

    @Query(nativeQuery = true,
    value = "SELECT u.*\n" +
            "FROM follows f JOIN \"user\" u ON f.followed_id = u.id\n" +
            "WHERE f.follower_id = :userId\n" +
            "ORDER BY f.date DESC\n")
    public List<User> findFollowedUsers(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "delete " +
            "from Follows " +
            "where followerId = :followerId and followedId = :followedId")
    void unFollow(@Param("followerId") int followerId, @Param("followedId") int followedId);

    @Query(value = "select u " +
            "from User u join Follows f on u.id = f.followerId " +
            "where f.followedId = :userId " +
            "order by f.date desc ")
    List<User> findFollowers(int userId);

    @Query(nativeQuery = true,
            value = "select u.*\n" +
                    "\n" +
                    "from \"user\" u\n" +
                    "         join (select tmp.id, max(date) date\n" +
                    "               from (\n" +
                    "                        select u.id, sm.date\n" +
                    "                        from \"user\" u\n" +
                    "                                 join sent_message sm on u.id = sm.receiver_id\n" +
                    "                        where sm.sender_id = :userId\n" +
                    "                        union\n" +
                    "                        select u.id, s.date\n" +
                    "                        from \"user\" u\n" +
                    "                                 join sent_message s on u.id = s.sender_id\n" +
                    "                        where s.receiver_id = :userId\n" +
                    "                    ) as tmp\n" +
                    "               group by tmp.id\n" +
                    ") tmp on u.id = tmp.id\n" +
                    "order by tmp.date desc\n"
    )
    List<User> getMessagedUsers(@Param("userId") int userId);

}
