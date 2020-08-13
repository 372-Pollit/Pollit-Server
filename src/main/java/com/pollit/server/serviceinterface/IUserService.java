package com.pollit.server.serviceinterface;


import com.pollit.server.model.User;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User isUser(String username, String password);

    public List<User> search(String searchString, int pageNumber);

    User findById(int id);

    List<User> findFollowedUsers(int userId);

    void unFollow(HashMap req);

    List<User> findFollowers(int userId);

    void update(HashMap req);
}
