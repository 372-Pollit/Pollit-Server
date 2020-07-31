package com.pollit.server.repository;

import com.pollit.server.customModel.Trend;
import com.pollit.server.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SurveyRepository extends CrudRepository<Survey, Integer> {

    @Query(nativeQuery = true,
    value = "select s_with_vote.id as id, s_with_vote.explanation as explanation, s_with_vote.post_date as postDate," +
            "s_with_vote.due_date as dueDate, vote_count as\n" +
            "    voteCount,\n" +
            "       comment_count as\n" +
            "    commentCount\n" +
            "from (select s.id, s.explanation ,s.post_date\\:\\:date, s.due_date\\:\\:date, count(*) vote_count\n" +
            "      from survey s\n" +
            "               join vote v on s.id = v.survey_id\n" +
            "      group by s.id\n" +
            "      order by s.post_date\\:\\:date desc) s_with_vote\n" +
            "         join (\n" +
            "    select s.id, s.post_date\\:\\:date, count(*) comment_count\n" +
            "    from survey s\n" +
            "             join comment c on s.id = c.survey_id\n" +
            "    group by s.id\n" +
            "\n" +
            "    order by s.post_date\\:\\:date desc) s_with_comment on s_with_vote.id = s_with_comment.id\n" +
            "order by s_with_vote.post_date desc ,vote_count desc, comment_count desc;\n" +
            "\n")
    public List<Trend> findTrends();

}
