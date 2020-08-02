package com.pollit.server.service;

import com.pollit.server.model.User;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

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

}
