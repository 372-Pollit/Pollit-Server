package com.pollit.server.service;

import com.pollit.server.customModel.Trend;
import com.pollit.server.repository.SurveyRepository;
import com.pollit.server.serviceinterface.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService implements ISurveyService {

    public final int PAGE_SIZE = 20;

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<Trend> findTrends(int pageNumber) {
        return surveyRepository.findTrends(pageNumber * PAGE_SIZE);
    }
}
