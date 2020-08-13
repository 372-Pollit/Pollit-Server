package com.pollit.server.service;

import com.pollit.server.model.User;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.IUserService;
import com.pollit.server.util.Crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> search(String searchString, int pageNumber) {
        return repository.search(searchString, pageNumber);
    }

    @Override
    public User findById(int id) {
        return repository.findById(id).get();
    }

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
        repository.unFollow(followerId, followedId);
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
}
