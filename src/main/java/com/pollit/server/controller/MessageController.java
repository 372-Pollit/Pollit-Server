package com.pollit.server.controller;

import com.pollit.server.model.SentMessage;
import com.pollit.server.model.User;
import com.pollit.server.serviceinterface.ISentMessageService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin("*")
public class MessageController {

    private final ISentMessageService sentMessageService;

    public MessageController(ISentMessageService sentMessageService) {
        this.sentMessageService = sentMessageService;
    }

    @GetMapping("/messagedUsers")
    public List<User> getMessagedUsers(@Param("userId") int userId) {
        return sentMessageService.getMessagedUsers(userId);
    }

    @GetMapping("/chat")
    public List<SentMessage> getChat(@Param("curUserId") int curUserId, @Param("activeUserId") int activeUserId) {
        return sentMessageService.getChat(curUserId, activeUserId);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody HashMap req) {
        sentMessageService.sendMessage(req);
    }

}
