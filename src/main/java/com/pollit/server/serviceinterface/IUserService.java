package com.pollit.server.serviceinterface;


import com.pollit.server.model.Admin;
import com.pollit.server.model.Moderator;
import com.pollit.server.model.User;

import java.util.HashMap;
import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User isUser(String username, String password);

    public Moderator isModerator(int id);

    public Admin isAdmin(int id);

    public List<User> search(String searchString, int pageNumber);

    User findById(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findFollowedUsers(int userId);

    public boolean isXFollowingY(int xId, int yId);

    void unFollow(HashMap req);

    void follow(HashMap req);

    List<User> findFollowers(int userId);

    void update(HashMap req);

    void save(HashMap req);

    boolean isBlocked(int id);

    List<User> blockedUsers();
}
