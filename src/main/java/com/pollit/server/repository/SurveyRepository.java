package com.pollit.server.repository;

import com.pollit.server.customModel.Trend;
import com.pollit.server.customModel.VotedSurveys;
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
            "   WHERE lower(s.title) LIKE %:search%\n" +
            "), in_tag AS (\n" +
            "   SELECT s.id, TRUE is_in_tag\n" +
            "   FROM survey s\n" +
            "   WHERE s.id IN (\n" +
            "       SELECT survey_id\n" +
            "       FROM survey_tag\n" +
            "       WHERE lower(survey_tag.tag) LIKE %:search%\n" +
            "   )\n" +
            "), in_explanation AS (\n" +
            "   SELECT s.id, TRUE is_in_explanation\n" +
            "   FROM survey s\n" +
            "   WHERE lower(s.explanation) LIKE %:search%\n" +
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

    @Query(nativeQuery = true,
    value = "WITH votes_of_user AS (\n" +
            "   SELECT v.survey_id, v.option_id, v.date\n" +
            "   FROM vote v\n" +
            "   WHERE v.user_id = :userId\n" +
            ")\n" +
            "SELECT s.id as id, s.title as title, u.username as username, s.explanation as explanation, s" +
            ".post_date\\:\\:date" +
            " " +
            "as postDate, s.due_date\\:\\:date as dueDate, s_with_votes.count as voteCount, s_with_comments.count as " +
            "commentCount, " +
            " o.option" +
            " as option\n" +
            "FROM survey s JOIN votes_of_user v ON s.id = v.survey_id JOIN survey_option o ON v.option_id = o.id\n" +
            "join (select s.id, count(*) from survey s join comment c on s.id = c.survey_id\n" +
            "group by s.id) s_with_votes on s.id = s_with_votes.id join (select s.id, count(*)\n" +
            "from survey s join comment c2 on s.id = c2.survey_id group by s.id) s_with_comments on s.id = " +
            "s_with_comments.id\n" +
            "join \"user\" u on s.poster_id = u.id\n" +
            "ORDER BY v.date DESC\n" +
            "limit 20\n" +
            "offset :pageNumber")
    public List<VotedSurveys> getVotedSurveys(@Param("userId") int userId, @Param("pageNumber") int pageNumber);


    @Query(nativeQuery = true,
    value = "select s.id                 as id,\n" +
            "       s.title              as title,\n" +
            "       s.due_date\\:\\:date     as dueDate,\n" +
            "       s.post_date\\:\\:date    as postDate,\n" +
            "       u.username           as username,\n" +
            "       s.explanation        as explanation,\n" +
            "       s_with_vote.count    as voteCount,\n" +
            "       s_with_comment.count as commentCount\n" +
            "\n" +
            "from survey s\n" +
            "         join (select s.id, count(v.id)\n" +
            "               from survey s\n" +
            "                        left join vote v on s.id = v.survey_id\n" +
            "               group by s.id) s_with_vote on s.id = s_with_vote.id\n" +
            "         join (select s.id, count(v.id)\n" +
            "               from survey s\n" +
            "                        left join comment v on s.id = v.survey_id\n" +
            "               group by s.id) s_with_comment on s.id = s_with_comment.id\n" +
            "         join \"user\" u on s.poster_id = u.id\n" +
            "where s.poster_id = :userId\n" +
            "order by s.post_date desc\n" +
            "limit 20\n" +
            "offset :pageNumber" +
            "\n" +
            "\n")
    public List<Trend> getPostedSurveys(@Param("userId") int userId, @Param("pageNumber") int pageNumber);

    @Query(nativeQuery = true,
        value = "select s.id              as id,\n" +
                "       s.title           as title,\n" +
                "       u.username        as username,\n" +
                "       s.explanation     as explanation,\n" +
                "       s.post_date\\:\\:date as postDate,\n" +
                "       s.due_date\\:\\:date  as dueDate,\n" +
                "       tmp.vote_count    as voteCount,\n" +
                "       tmp.comment_count as commentCount\n" +
                "from (select distinct  q1.username, q1.survey_id,  q_vote.vote_count, q_comment.comment_count\n" +
                "      from ((select 1 as flag, u.username, s.id as survey_id, s.post_date as \"date\", u2.username as user_or_category\n" +
                "             from \"user\" u, follows f, survey s, \"user\" u2\n" +
                "             where u.id = f.follower_id and u.id = :userId and s.poster_id = f.followed_id and u2.id = f.followed_id)\n" +
                "            union\n" +
                "            ((select 2 as flag, u.username, v.survey_id as survey_id, v.\"date\" as \"date\", u2.username\n" +
                "              from \"user\" u, follows f, vote v, \"user\" u2\n" +
                "              where u.id = f.follower_id and u.id = :userId and v.survey_id = f.followed_id and u2.id = f.followed_id)\n" +
                "             union\n" +
                "             (select 3 as flag, u.username, s.id as survey_id, s.post_date as \"date\", c.\"name\"\n" +
                "              from \"user\" u, subs sub, category c, survey s, belongs b\n" +
                "              where u.id = sub.user_id and u.id = :userId and c.id = sub.category_id\n" +
                "                and s.id = b.survey_id and b.category_id = sub.category_id))\n" +
                "            order by \"date\" desc) as q1,\n" +
                "\n" +
                "           (select s.id, count(v.id) as vote_count\n" +
                "            from survey s, vote v\n" +
                "            where s.id = v.survey_id\n" +
                "            group by s.id) as q_vote,\n" +
                "\n" +
                "           (select s.id, count(c.id) as comment_count\n" +
                "            from survey s, \"comment\" c\n" +
                "            where s.id = c.survey_id\n" +
                "            group by s.id) as q_comment\n" +
                "      where q_comment.id = q1.survey_id and q_vote.id = q1.survey_id\n" +
                "     ) tmp join survey s on tmp.survey_id = s.id join \"user\" u on s.poster_id = u.id\n" +
                "order by s.due_date - current_date DESC, tmp.vote_count, tmp.comment_count " +
                "limit 20 " +
                "offset :pageNumber\n"
    )
    List<Trend> getSurveysForUser(@Param("pageNumber") int pageNumber, @Param("userId") int userId);

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
                    "where s_with_vote.id in (select s.id\n" +
                    "from survey s join belongs b on s.id = b.survey_id\n" +
                    "where b.category_id = :categoryId)\n" +
                    "order by s_with_vote.due_date - current_date desc ,vote_count desc, comment_count desc\n" +
                    "limit 20\n" +
                    "offset :pageNumber")
    List<Trend> searchByCategory(@Param("categoryId") int categoryId, @Param("pageNumber") int pageNumber);
}
