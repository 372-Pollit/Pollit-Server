package com.pollit.server.repository;

import com.pollit.server.model.SurveyOption;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SurveyOptionRepository extends CrudRepository<SurveyOption, Integer> {

    @Query(
            value = "select o " +
                    "from SurveyOption o " +
                    "where o.option = :option and o.surveyId = :surveyId"
    )
    SurveyOption getByOptionAndSurveyId(@Param("option") String option, @Param("surveyId") int surveyId);

}
