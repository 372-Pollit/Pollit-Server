package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "deleted_survey", schema = "public", catalog = "pollit")
public class DeletedSurvey {
    private int id;
    private int moderatorId;
    private int surveyId;
    private Timestamp date;
    private String cause;
    private User moderator;
    private Survey survey;

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
    @Column(name = "moderator_id", nullable = false, insertable = false, updatable = false)
    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
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
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "cause", nullable = false, length = 100)
    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeletedSurvey that = (DeletedSurvey) o;

        if (id != that.id) return false;
        if (moderatorId != that.moderatorId) return false;
        if (surveyId != that.surveyId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + moderatorId;
        result = 31 * result + surveyId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "moderator_id", referencedColumnName = "id", nullable = false)
    public User getModerator() {
        return moderator;
    }

    public void setModerator(User userByModeratorId) {
        this.moderator = userByModeratorId;
    }

    @ManyToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "id", nullable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey surveyBySurveyId) {
        this.survey = surveyBySurveyId;
    }
}
