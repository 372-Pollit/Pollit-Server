package com.pollit.server.service;

import com.pollit.server.customModel.Trend;
import com.pollit.server.customModel.VotedSurveys;
import com.pollit.server.model.Survey;
import com.pollit.server.model.SurveyOption;
import com.pollit.server.model.SurveyTag;
import com.pollit.server.repository.SurveyOptionRepository;
import com.pollit.server.repository.SurveyRepository;
import com.pollit.server.repository.SurveyTagRepository;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class SurveyService implements ISurveyService {

    public final int PAGE_SIZE = 20;

    private final SurveyRepository surveyRepository;
    private final SurveyOptionRepository surveyOptionRepository;
    private final UserRepository userRepository;
    private final SurveyTagRepository surveyTagRepository;

    public SurveyService(SurveyRepository surveyRepository, SurveyOptionRepository surveyOptionRepository, UserRepository userRepository, SurveyTagRepository surveyTagRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyOptionRepository = surveyOptionRepository;
        this.userRepository = userRepository;
        this.surveyTagRepository = surveyTagRepository;
    }

    @Override
    public List<Trend> findTrends(int pageNumber) {
        return surveyRepository.findTrends(pageNumber * PAGE_SIZE);
    }

    @Override
    public List<Trend> search(String search, int pageNumber) {
        return surveyRepository.search(search.toLowerCase(), pageNumber * PAGE_SIZE);
    }

    @Override
    public List<VotedSurveys> getVotedSurveys(int userId, int pageNumber) {
        return surveyRepository.getVotedSurveys(userId, pageNumber * PAGE_SIZE);
    }

    @Override
    public List<Trend> getPostedSurveys(int userId, int pageNumber) {
        return surveyRepository.getPostedSurveys(userId, pageNumber * PAGE_SIZE);
    }

    @Override
    public List<Trend> getSurveysForUser(int pageNumber, int userId) {
        return surveyRepository.getSurveysForUser(pageNumber * PAGE_SIZE, userId);
    }

    @Override
    public List<Trend> searchByCategory(int pageNumber, int categoryId) {
        return surveyRepository.searchByCategory(categoryId, pageNumber * PAGE_SIZE);
    }

    @Override
    @Transactional
    @Modifying
    public void post(HashMap req) {
        Survey s = new Survey();
        String dueDate = (String) req.get("dueDate");
        String startingDate = (String) req.get("startDate");
        String explanation = (String) req.get("explanation");
        int posterId = Integer.valueOf(req.get("posterId").toString());
        String title = (String) req.get("title");
        s.setTitle(title);
        s.setPosterUser(userRepository.findById(posterId).get());
        s.setExplanation(explanation);
        s.setDueDate(Timestamp.valueOf(((String) dueDate).replace("T", " ") + ":00"));
        s.setStartingDate(Timestamp.valueOf(((String) startingDate).replace("T", " ") + ":00"));
        s.setPostDate(new Timestamp(System.currentTimeMillis()));
        surveyRepository.save(s);
        for (String str :
                ((List<String>) req.get("options"))) {
            SurveyOption option = new SurveyOption();
            option.setOption(str);
            option.setSurveyId(s.getId());
            surveyOptionRepository.save(option);
        }
        for (String str :
                ((List<String>) req.get("tags"))) {
            SurveyTag tag = new SurveyTag();
            tag.setSurveyId(s.getId());
            tag.setTag(str);
            surveyTagRepository.save(tag);
        }
    }

    @Override
    public Survey findById(int surveyId) {
        return surveyRepository.findById(surveyId).get();
    }

    @Override
    public List<SurveyOption> getOptions(int surveyId) {
        return surveyRepository.getOptions(surveyId);
    }

}
