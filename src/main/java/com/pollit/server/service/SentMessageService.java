package com.pollit.server.service;

import com.pollit.server.model.SentMessage;
import com.pollit.server.model.User;
import com.pollit.server.repository.MessageRepository;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.ISentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class SentMessageService implements ISentMessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public SentMessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getMessagedUsers(int userId) {
        return userRepository.getMessagedUsers(userId);
    }

    @Override
    public List<SentMessage> getChat(int curUserId, int activeUserId) {
        return messageRepository.getChat(curUserId, activeUserId);
    }

    @Override
    @Transactional
    public void sendMessage(HashMap req) {
        int senderId = Integer.valueOf(req.get("senderId").toString());
        int receiverId = Integer.valueOf(req.get("receiverId").toString());
        String content = req.get("content").toString();
        SentMessage sm = new SentMessage();
        sm.setContent(content);
        sm.setDate(new Timestamp(System.currentTimeMillis()));
        sm.setSender(userRepository.findById(senderId).get());
        sm.setReceiver(userRepository.findById(receiverId).get());
        messageRepository.save(sm);
    }
}
