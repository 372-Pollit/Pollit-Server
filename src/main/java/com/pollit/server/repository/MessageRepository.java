package com.pollit.server.repository;

import com.pollit.server.model.SentMessage;
import com.pollit.server.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<SentMessage, Integer> {


    @Query(
            value = "select sm " +
                    "from SentMessage sm " +
                    "where (sm.senderId = :curUserId and sm.receiverId = :activeUserId) or " +
                    "(sm.receiverId = :curUserId and sm.senderId = :activeUserId) " +
                    "order by sm.date"
    )
    List<SentMessage> getChat(@Param("curUserId") int curUserId, @Param("activeUserId") int activeUserId);
}
