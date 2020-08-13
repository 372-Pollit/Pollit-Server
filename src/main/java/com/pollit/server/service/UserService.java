package com.pollit.server.service;

import com.pollit.server.model.Admin;
import com.pollit.server.model.Moderator;
import com.pollit.server.model.User;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.IUserService;
import com.pollit.server.util.Crud;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService extends Crud implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User isUser(String username, String password) {
        return repository.isUser(username, password);
    }

    @Override
    public Moderator isModerator(int id) {
        return repository.isModerator(id);
    }

    @Override
    public Admin isAdmin(int id) {
        return repository.isAdmin(id);
    }

    @Override
    public List<User> search(String searchString, int pageNumber) {
        return repository.search(searchString.toLowerCase(), pageNumber);
    }

    @Override
    public User findById(int id) {
        return repository.findById(id).get();
    }

    public User findByUsername(String username) { return repository.findByUsername(username); }

    public User findByEmail(String email) { return repository.findByEmail(email); }

    @Override
    public List<User> findFollowedUsers(int userId) {
        return repository.findFollowedUsers(userId);
    }

    @Override
    public boolean isXFollowingY(int xId, int yId) {
        return repository.isXFollowingY(xId, yId);
    }

    @Override
    public void unFollow(HashMap req) {
        int followerId = Integer.valueOf(req.get("followerId").toString());
        int followedId = Integer.valueOf(req.get("followedId").toString());
        repository.unFollow(followerId, followedId);
    }

    @Override
    public void follow(HashMap req) {
        int followerId = Integer.valueOf(req.get("followerId").toString());
        int followedId = Integer.valueOf(req.get("followedId").toString());
        repository.follow(followerId, followedId);
    }

    @Override
    public List<User> findFollowers(int userId) {
        return repository.findFollowers(userId);
    }

    @Override
    @Modifying
    @Transactional
    public void update(HashMap req) {
        User u = repository.findById(Integer.valueOf(req.get("id").toString())).get();
        update(u, req);
    }

    @Modifying
    @Transactional
    public void save(HashMap req) {
        User u = new User();
        u.setUsername(req.get("username").toString());
        System.out.println(req.get("username").toString());
        u.setPassword(req.get("password").toString());
        System.out.println(req.get("password").toString());
        u.setEmail(req.get("email").toString());
        System.out.println(req.get("email").toString());
        u.setFirstName(req.get("first_name").toString());
        System.out.println(req.get("first_name").toString());
        u.setLastName(req.get("last_name").toString());
        System.out.println(req.get("last_name").toString());
        u.setSex(req.get("sex").toString());
        System.out.println(req.get("sex").toString());
        u.setBirthDate(Date.valueOf(req.get("birth_date").toString()));
        System.out.println(req.get("birth_date").toString());
        u.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        u.setBlocked(false);
        repository.save(u);
    }
}
