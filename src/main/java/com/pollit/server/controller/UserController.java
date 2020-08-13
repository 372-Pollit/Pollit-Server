package com.pollit.server.controller;

import com.pollit.server.model.Admin;
import com.pollit.server.model.Moderator;
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

    @GetMapping("/getModerators")
    public List<User> getModerators() {
        return userService.getModerators();
    }

    @GetMapping("/nonModeratorUsers")
    public List<User> nonModeratorUsers() {
        return userService.nonModeratorUsers();
    }

    @GetMapping("/isUser")
    public User isUser(@Param("username") String username, @Param("password") String password) {
        return userService.isUser(username, password);
    }

    @GetMapping("/isModerator")
    public Moderator isModerator(@Param("id") int id) {
        return userService.isModerator(id);
    }

    @GetMapping("/isAdmin")
    public Admin isAdmin(@Param("id") int id) {
        return userService.isAdmin(id);
    }

    @GetMapping("/search")
    public List<User> search(@Param("searchString") String searchString, @Param("pageNumber") int pageNumber) {
        return userService.search(searchString.substring(1), pageNumber);
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

    @GetMapping("/isXFollowingY")
    public boolean isXFollowingY(@Param("xId") int xId, @Param("yId") int yId) {
        return userService.isXFollowingY(xId, yId);
    }

    @PostMapping("/unFollow")
    public void unFollow(@RequestBody HashMap req) {
        userService.unFollow(req);
    }

    @PostMapping("/follow")
    public void follow(@RequestBody HashMap req) {
        userService.follow(req);
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

    @GetMapping("/isBlocked")
    public boolean isBlocked(@Param("id") int id) {
        return userService.isBlocked(id);
    }

    @GetMapping("/blockedUsers")
    public List<User> blockedUsers() { return userService.blockedUsers(); }
}
