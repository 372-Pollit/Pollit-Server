package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "vote", schema = "public")
public class Vote {
    private int id;
    private int surveyId;
    private int userId;
    private int optionId;
    private Timestamp date;
    private User user;
    private SurveyOption option;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "survey_id", nullable = false, insertable = false, updatable = false)
    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "option_id", nullable = false, insertable = false, updatable = false)
    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (id != vote.id) return false;
        if (surveyId != vote.surveyId) return false;
        if (userId != vote.userId) return false;
        if (optionId != vote.optionId) return false;
        if (date != null ? !date.equals(vote.date) : vote.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + surveyId;
        result = 31 * result + userId;
        result = 31 * result + optionId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)
    public SurveyOption getOption() {
        return option;
    }

    public void setOption(SurveyOption surveyOptionByOptionId) {
        this.option = surveyOptionByOptionId;
    }
}
