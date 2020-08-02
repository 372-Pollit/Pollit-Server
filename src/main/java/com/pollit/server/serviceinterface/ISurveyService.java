package com.pollit.server.serviceinterface;

import com.pollit.server.customModel.Trend;

import java.util.List;

public interface ISurveyService {
    public List<Trend> findTrends(int pageNumber);

    public List<Trend> search(String search, int pageNubmer);
}
