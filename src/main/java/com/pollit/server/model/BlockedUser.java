package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blocked_user", schema = "public", catalog = "pollit")
public class BlockedUser {
    private int id;
    private int moderatorId;
    private int userId;
    private Timestamp date;
    private String cause;
    private Moderator moderator;
    private User user;
    private boolean isBlocked;

    @Column(name = "is_blocked", nullable = false)
    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

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
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "cause", nullable = false, length = 200)
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

        BlockedUser that = (BlockedUser) o;

        if (id != that.id) return false;
        if (moderatorId != that.moderatorId) return false;
        if (userId != that.userId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + moderatorId;
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "moderator_id", referencedColumnName = "user_id", nullable = false)
    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderatorByModeratorId) {
        this.moderator = moderatorByModeratorId;
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
