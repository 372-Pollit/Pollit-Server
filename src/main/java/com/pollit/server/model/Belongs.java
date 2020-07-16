package com.pollit.server.model;

import javax.persistence.*;

@Entity
@Table(name = "belongs", schema = "public")
public class Belongs {
    private int id;
    private int categoryId;
    private int surveyId;
    private Category category;

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
    @Column(name = "category_id", nullable = false, insertable = false, updatable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "survey_id", nullable = false, insertable = false, updatable =  false)
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

        Belongs belongs = (Belongs) o;

        if (id != belongs.id) return false;
        if (categoryId != belongs.categoryId) return false;
        if (surveyId != belongs.surveyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + categoryId;
        result = 31 * result + surveyId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryByCategoryId) {
        this.category = categoryByCategoryId;
    }

}
