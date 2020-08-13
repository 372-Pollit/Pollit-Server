package com.pollit.server.serviceinterface;

import com.pollit.server.customModel.VoteStatistics;
import com.pollit.server.model.Vote;

import java.util.HashMap;
import java.util.List;

public interface IVoteService {

    Vote getVotedOption(int surveyId, int userId);

    void sendVote(HashMap req);

    List<VoteStatistics> getVoteStatistics(int surveyId);
}
