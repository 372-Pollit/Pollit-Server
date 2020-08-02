package com.pollit.server.controller;

import com.pollit.server.customModel.Trend;
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
}
