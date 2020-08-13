package com.pollit.server.serviceinterface;

import com.pollit.server.customModel.Trend;
import com.pollit.server.customModel.VotedSurveys;
import com.pollit.server.model.Survey;
import com.pollit.server.model.SurveyOption;

import java.util.HashMap;
import java.util.List;

public interface ISurveyService {
    public List<Trend> findTrends(int pageNumber);

    public List<Trend> search(String search, int pageNubmer);

    public List<VotedSurveys> getVotedSurveys(int userId, int pageNumber);

    public List<Trend> getPostedSurveys(int userId, int pageNumber);

    List<Trend> getSurveysForUser(int pageNumber, int userId);

    List<Trend> searchByCategory(int pageNumber, int categoryId);

    void post(HashMap req);

    Survey findById(int surveyId);

    List<SurveyOption> getOptions(int surveyId);
}
