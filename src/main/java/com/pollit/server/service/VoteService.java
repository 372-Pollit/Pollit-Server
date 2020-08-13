package com.pollit.server.service;

import com.pollit.server.customModel.VoteStatistics;
import com.pollit.server.model.SurveyOption;
import com.pollit.server.model.Vote;
import com.pollit.server.repository.SurveyOptionRepository;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.repository.VoteRepository;
import com.pollit.server.serviceinterface.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class VoteService implements IVoteService {

    private final VoteRepository voteRepository;
    private final SurveyOptionRepository surveyOptionRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, SurveyOptionRepository surveyOptionRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.surveyOptionRepository = surveyOptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vote getVotedOption(int surveyId, int userId) {
        return voteRepository.getVotedOption(surveyId, userId);
    }

    @Override
    @Transactional
    public void sendVote(HashMap req) {
        int surveyId = Integer.valueOf(req.get("surveyId").toString());
        String option = (String) req.get("option");
        int userId = Integer.valueOf(req.get("userId").toString());
        SurveyOption so = surveyOptionRepository.getByOptionAndSurveyId(option, surveyId);
        Vote v = new Vote();
        v.setDate(new Timestamp(System.currentTimeMillis()));
        v.setOption(so);
        v.setSurveyId(surveyId);
        v.setUser(userRepository.findById(userId).get());
        voteRepository.save(v);
    }

    @Override
    public List<VoteStatistics> getVoteStatistics(int surveyId) {
        return voteRepository.getVoteStatistics(surveyId);
    }

}
