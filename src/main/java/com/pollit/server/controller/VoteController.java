package com.pollit.server.controller;

import com.pollit.server.customModel.VoteStatistics;
import com.pollit.server.model.Vote;
import com.pollit.server.serviceinterface.IVoteService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/vote")
@CrossOrigin("*")
public class VoteController {

    private final IVoteService voteService;

    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/votedOption")
    public Vote getVotedOption(@Param("surveyId") int surveyId, @Param("userId") int userId) {
        return voteService.getVotedOption(surveyId, userId);
    }

    @PostMapping("/send")
    public void sendVote(@RequestBody HashMap req) {
        voteService.sendVote(req);
    }

    @GetMapping("/getVoteStatistics")
    public List<VoteStatistics> getVoteStatistics(@Param("surveyId") int surveyId) {
        return voteService.getVoteStatistics(surveyId);
    }

}
