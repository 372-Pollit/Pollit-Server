package com.pollit.server.serviceinterface;

import com.pollit.server.model.SentMessage;
import com.pollit.server.model.User;

import java.util.HashMap;
import java.util.List;

public interface ISentMessageService {

    List<User> getMessagedUsers(int userId);

    List<SentMessage> getChat(int curUserId, int activeUserId);

    void sendMessage(HashMap req);
}
