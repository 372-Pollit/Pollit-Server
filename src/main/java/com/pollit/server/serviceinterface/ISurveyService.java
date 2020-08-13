package com.pollit.server.serviceinterface;

import com.pollit.server.customModel.Trend;
import com.pollit.server.customModel.VotedSurveys;

import java.util.List;

public interface ISurveyService {
    public List<Trend> findTrends(int pageNumber);

    public List<Trend> search(String search, int pageNubmer);

    public List<VotedSurveys> getVotedSurveys(int userId, int pageNumber);

    public List<Trend> getPostedSurveys(int userId, int pageNumber);

    List<Trend> getSurveysForUser(int pageNumber, int userId);
}
