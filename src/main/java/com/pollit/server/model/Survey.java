package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "survey", schema = "public")
public class Survey {
    private int id;
    private Timestamp startingDate;
    private Timestamp dueDate;
    private String explanation;
    private String title;
    private int posterId;
    private Timestamp postDate;
    private Collection<Belongs> belongedCategories;
    private Collection<Comment> comments;
    private User posterUser;
    private Collection<Vote> votes;

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
    @Column(name = "starting_date", nullable = true)
    public Timestamp getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Timestamp startingDate) {
        this.startingDate = startingDate;
    }

    @Basic
    @Column(name = "due_date", nullable = true)
    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "explanation", nullable = true, length = 1000)
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "poster_id", nullable = false, insertable = false, updatable = false)
    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    @Basic
    @Column(name = "post_date", nullable = false)
    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (id != survey.id) return false;
        if (posterId != survey.posterId) return false;
        if (startingDate != null ? !startingDate.equals(survey.startingDate) : survey.startingDate != null)
            return false;
        if (dueDate != null ? !dueDate.equals(survey.dueDate) : survey.dueDate != null) return false;
        if (explanation != null ? !explanation.equals(survey.explanation) : survey.explanation != null) return false;
        if (title != null ? !title.equals(survey.title) : survey.title != null) return false;
        if (postDate != null ? !postDate.equals(survey.postDate) : survey.postDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startingDate != null ? startingDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + posterId;
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        return result;
    }

    @OneToMany
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    public Collection<Belongs> getBelongedCategories() {
        return belongedCategories;
    }

    public void setBelongedCategories(Collection<Belongs> belongsById) {
        this.belongedCategories = belongsById;
    }

    @OneToMany
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> commentsById) {
        this.comments = commentsById;
    }

    @ManyToOne
    @JoinColumn(name = "poster_id", referencedColumnName = "id", nullable = false)
    public User getPosterUser() {
        return posterUser;
    }

    public void setPosterUser(User userByPosterId) {
        this.posterUser = userByPosterId;
    }

    @OneToMany
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votesById) {
        this.votes = votesById;
    }
}
