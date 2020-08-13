package com.pollit.server.controller;

import com.pollit.server.serviceinterface.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/send")
    public void sendComment(@RequestBody HashMap req) {
        commentService.sendComment(req);
    }

}
