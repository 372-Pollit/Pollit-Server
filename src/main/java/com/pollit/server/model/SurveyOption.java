package com.pollit.server.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "survey_option", schema = "public", catalog = "pollit")
public class SurveyOption {
    private int id;
    private String option;
    private int surveyId;

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
    @Column(name = "option", nullable = false, length = 100)
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Basic
    @Column(name = "survey_id", nullable = false)
    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyOption that = (SurveyOption) o;

        if (id != that.id) return false;
        if (surveyId != that.surveyId) return false;
        if (option != null ? !option.equals(that.option) : that.option != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (option != null ? option.hashCode() : 0);
        result = 31 * result + surveyId;
        return result;
    }

}
