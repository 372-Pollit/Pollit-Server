package com.pollit.server.controller;

import com.pollit.server.customModel.Trend;
import com.pollit.server.customModel.VotedSurveys;
import com.pollit.server.repository.SurveyRepository;
import com.pollit.server.serviceinterface.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
@CrossOrigin("*")
public class SurveyController {

    private final ISurveyService surveyService;

    public SurveyController(ISurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/trends")
    public List<Trend> findTrends(@Param("pageNumber") int pageNumber) {
        return surveyService.findTrends(pageNumber);
    }

    @GetMapping("/search")
    public List<Trend> search(@Param("search") String search, @Param("pageNumber") int pageNumber) {
        return surveyService.search(search, pageNumber);
    }

    @GetMapping("/votedSurveys")
    public List<VotedSurveys> getVotedSurveys(@Param("userId") int userId, @Param("pageNumber") int pageNumber) {
        return surveyService.getVotedSurveys(userId, pageNumber);
    }

    @GetMapping("/postedSurveys")
    public List<Trend> getPostedSurveys(@Param("userId") int userId, @Param("pageNumber") int pageNumber) {
        return surveyService.getPostedSurveys(userId, pageNumber);
    }

    @GetMapping("/forUser")
    public List<Trend> getSurveysForUser(@Param("pageNumber") int pageNumber, @Param("userId") int userId) {
        return surveyService.getSurveysForUser(pageNumber, userId);
    }
}
