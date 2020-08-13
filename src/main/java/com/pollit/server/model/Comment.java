package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "comment", schema = "public")
public class Comment {
    private int id;
    private Timestamp dateWritten;
    private Timestamp dateLastEdited;
    private String content;
    private Boolean isDeleted;
    private Integer parentCommentId;
    private int userId;
    private int surveyId;
    private Comment parentComment;
    private Collection<Comment> childComments;
    private User user;

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
    @Column(name = "date_written", nullable = false)
    public Timestamp getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(Timestamp dateWritten) {
        this.dateWritten = dateWritten;
    }

    @Basic
    @Column(name = "date_last_edited", nullable = true)
    public Timestamp getDateLastEdited() {
        return dateLastEdited;
    }

    public void setDateLastEdited(Timestamp dateLastEdited) {
        this.dateLastEdited = dateLastEdited;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "is_deleted", nullable = true)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "parent_comment_id", nullable = true ,insertable = false, updatable = false)
    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Basic
    @Column(name = "user_id", nullable = false ,insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (userId != comment.userId) return false;
        if (surveyId != comment.surveyId) return false;
        if (dateWritten != null ? !dateWritten.equals(comment.dateWritten) : comment.dateWritten != null) return false;
        if (dateLastEdited != null ? !dateLastEdited.equals(comment.dateLastEdited) : comment.dateLastEdited != null)
            return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (isDeleted != null ? !isDeleted.equals(comment.isDeleted) : comment.isDeleted != null) return false;
        if (parentCommentId != null ? !parentCommentId.equals(comment.parentCommentId) : comment.parentCommentId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateWritten != null ? dateWritten.hashCode() : 0);
        result = 31 * result + (dateLastEdited != null ? dateLastEdited.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (parentCommentId != null ? parentCommentId.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + surveyId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "id")
    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment commentByParentCommentId) {
        this.parentComment = commentByParentCommentId;
    }

    @OneToMany(mappedBy = "parentComment")
    public Collection<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(Collection<Comment> commentsById) {
        this.childComments = commentsById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }

}
