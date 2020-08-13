package com.pollit.server.repository;

import com.pollit.server.customModel.VoteStatistics;
import com.pollit.server.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Integer> {

    @Query(
            value = "select v " +
                    "from Vote v " +
                    "where v.surveyId = :surveyId and v.userId = :userId"
    )
    Vote getVotedOption(@Param("surveyId") int surveyId, @Param("userId") int userId);

    @Query(nativeQuery = true,
        value = "select *, 100*voteCount/totalVote as percentage\n" +
                "from (\n" +
                "         select option_id as optionId, count(id) as voteCount, (\n" +
                "             select count(id)\n" +
                "             from vote v\n" +
                "             where survey_id = :surveyId\n" +
                "             group by survey_id) as totalVote\n" +
                "         from vote v\n" +
                "         where survey_id = :surveyId\n" +
                "         group by survey_id, option_id) as tmp\n" +
                "order by optionId"
    )
    List<VoteStatistics> getVoteStatistics(@Param("surveyId") int surveyId);

}
