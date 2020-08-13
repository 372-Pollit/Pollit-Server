package com.pollit.server.controller;

import com.pollit.server.model.User;
import com.pollit.server.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/isUser")
    public User isUser(@Param("username") String username, @Param("password") String password) {
        return userService.isUser(username, password);
    }

    @GetMapping("/find")
    public User findUserById(@Param("id") int id) {
        return userService.findById(id);
    }

    @GetMapping("/findByUsername")
    public User findUserByUsername(@Param("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/findByEmail")
    public User findUserByEmail(@Param("email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/followedUsers")
    public List<User> findFollowedUsers(@Param("userId") int userId) {
        return userService.findFollowedUsers(userId);
    }

    @PostMapping("/unFollow")
    public void unFollow(@RequestBody HashMap req) {
        userService.unFollow(req);
    }

    @GetMapping("/followers")
    public List<User> findFollowers(@Param("userId") int userId) {
        return userService.findFollowers(userId);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody HashMap req) {
        userService.update(req);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody HashMap req) {
        userService.save(req);
    }
}
