package com.pollit.server.model;

import javax.persistence.*;

@Entity
@Table(name = "survey_tag", schema = "public", catalog = "pollit")
public class SurveyTag {
    private int id;
    private int surveyId;
    private String tag;

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
    @Column(name = "survey_id", nullable = false)
    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    @Basic
    @Column(name = "tag", nullable = false, length = 50)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyTag surveyTag = (SurveyTag) o;

        if (id != surveyTag.id) return false;
        if (surveyId != surveyTag.surveyId) return false;
        if (tag != null ? !tag.equals(surveyTag.tag) : surveyTag.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + surveyId;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
