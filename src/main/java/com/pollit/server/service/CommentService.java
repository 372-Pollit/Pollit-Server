package com.pollit.server.service;

import com.pollit.server.model.Comment;
import com.pollit.server.repository.CommentRepository;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void sendComment(HashMap req) {
        Comment c = new Comment();
        c.setContent(req.get("content").toString());
        c.setDateLastEdited(new Timestamp(System.currentTimeMillis()));
        c.setDateWritten(new Timestamp(System.currentTimeMillis()));
        c.setSurveyId(Integer.valueOf(req.get("surveyId").toString()));
        c.setUser(userRepository.findById(Integer.valueOf(req.get("userId").toString())).get());
        c.setDeleted(false);
        commentRepository.save(c);
    }
}
