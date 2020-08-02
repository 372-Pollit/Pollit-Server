package com.pollit.server.repository;

import com.pollit.server.customModel.Trend;
import com.pollit.server.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyRepository extends CrudRepository<Survey, Integer> {

    @Query(nativeQuery = true,
    value = "select s_with_vote.id as id, s_with_vote.title as title, u.username as username, s_with_vote.explanation" +
            " as " +
            "explanation, " +
            "s_with_vote" +
            ".post_date as postDate," +
            "s_with_vote.due_date as dueDate, vote_count as\n" +
            "    voteCount,\n" +
            "       comment_count as\n" +
            "    commentCount\n" +
            "from (select s.id, s.title, s.poster_id, s.explanation ,s.post_date\\:\\:date, s.due_date\\:\\:date, " +
            "count(*) " +
            "vote_count\n" +
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
            "join \"user\" u on s_with_vote.poster_id = u.id "+
            "order by s_with_vote.due_date - current_date desc ,vote_count desc, comment_count desc\n" +
            "limit 20\n" +
            "offset :pageNumber")
    public List<Trend> findTrends(@Param("pageNumber") int pageNumber );

    @Query(nativeQuery = true,
    value = "WITH in_title AS (\n" +
            "   SELECT s.id, TRUE is_in_title\n" +
            "   FROM survey s\n" +
            "   WHERE s.title LIKE %:search%\n" +
            "), in_tag AS (\n" +
            "   SELECT s.id, TRUE is_in_tag\n" +
            "   FROM survey s\n" +
            "   WHERE s.id IN (\n" +
            "       SELECT survey_id\n" +
            "       FROM survey_tag\n" +
            "       WHERE survey_tag.tag LIKE %:search%\n" +
            "   )\n" +
            "), in_explanation AS (\n" +
            "   SELECT s.id, TRUE is_in_explanation\n" +
            "   FROM survey s\n" +
            "   WHERE s.explanation LIKE %:search%\n" +
            "), in_title_and_tag AS (\n" +
            "   SELECT \"id\", is_in_title, is_in_tag, FALSE is_in_explanation\n" +
            "   FROM in_title NATURAL JOIN in_tag\n" +
            "   WHERE \"id\" NOT IN (SELECT \"id\" FROM in_explanation)\n" +
            "), in_title_and_explanation AS (\n" +
            "   SELECT \"id\", is_in_title, FALSE is_in_tag, is_in_explanation\n" +
            "   FROM in_title NATURAL JOIN in_explanation\n" +
            "   WHERE \"id\" NOT IN (SELECT \"id\" FROM in_tag)\n" +
            "), in_tag_and_explanation AS (\n" +
            "   SELECT \"id\", FALSE is_in_title, is_in_tag, is_in_explanation\n" +
            "   FROM in_tag NATURAL JOIN in_explanation\n" +
            "   WHERE \"id\" NOT IN (SELECT \"id\" FROM in_title)\n" +
            "),  only_title AS (\n" +
            "   SELECT \"id\", is_in_title, FALSE is_in_tag, FALSE is_in_explanation\n" +
            "   FROM in_title\n" +
            "   WHERE \"id\" NOT IN (SELECT \"id\" FROM in_tag) AND \"id\" NOT IN (SELECT \"id\" FROM in_title)\n" +
            "),  only_tag AS (\n" +
            "   SELECT \"id\", FALSE is_in_title, is_in_tag, FALSE is_in_explanation\n" +
            "   FROM in_tag\n" +
            "   WHERE \"id\" NOT IN (SELECT \"id\" FROM in_title) AND \"id\" NOT IN (SELECT \"id\" FROM in_explanation)\n" +
            "),  only_explanation AS (\n" +
            "   SELECT \"id\", FALSE is_in_title, FALSE is_in_tag, is_in_explanation\n" +
            "   FROM in_explanation\n" +
            "   WHERE \"id\" NOT IN (SELECT \"id\" FROM in_title) AND \"id\" NOT IN (SELECT \"id\" FROM in_tag)\n" +
            "), in_all AS (\n" +
            "   SELECT \"id\", is_in_title, is_in_tag, is_in_explanation\n" +
            "   FROM in_title NATURAL JOIN in_tag NATURAL JOIN in_explanation\n" +
            "), combine AS (\n" +
            "   SELECT *\n" +
            "   FROM (\n" +
            "            SELECT * FROM in_all\n" +
            "            UNION\n" +
            "            SELECT * FROM in_title_and_tag\n" +
            "            UNION\n" +
            "            SELECT * FROM in_title_and_explanation\n" +
            "            UNION\n" +
            "            SELECT * FROM in_tag_and_explanation\n" +
            "            UNION\n" +
            "            SELECT * FROM only_title\n" +
            "            UNION\n" +
            "            SELECT * FROM only_tag\n" +
            "            UNION\n" +
            "            SELECT * FROM only_explanation\n" +
            "        ) foo\n" +
            ")\n" +
            "\n" +
            "\n" +
            "SELECT s.id, title, u.username as username, s.explanation,\n" +
            "\t\ts.post_date\\:\\:date as postDate, due_date\\:\\:date as dueDate, s_with_vote.count as voteCount,\n" +
            "\t\ts_with_comment.count as commentCount\n" +
            "FROM (\n" +
            "        SELECT *\n" +
            "        FROM combine\n" +
            "    ) c NATURAL JOIN survey s join \"user\" u on s.poster_id = u.id\n" +
            "\tjoin (select s.id, count(*)\n" +
            "\t\t from survey s join vote v on s.id = v.survey_id\n" +
            "\t\t group by s.id) s_with_vote on s_with_vote.id = s.id\n" +
            "\t join (select s.id, count(*)\n" +
            "\t\t  from survey s join comment c on s.id = c.survey_id\n" +
            "\t\t  group by s.id) s_with_comment on s_with_comment.id = s.id \n" +
            "ORDER BY c.is_in_title DESC, c.is_in_tag DESC, c.is_in_explanation DESC, s.due_date - current_date DESC," +
            " s.id ASC\n" +
            "limit 20\n" +
            "offset :pageNumber")
    public List<Trend> search(@Param("search") String search, @Param("pageNumber") int pageNumber);

}
