package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "follows", schema = "public")
public class Follows {
    private int id;
    private int followerId;
    private int followedId;
    private Timestamp date;
    private User follower;
    private User followed;

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
    @Column(name = "follower_id", nullable = false ,insertable = false, updatable = false)
    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    @Basic
    @Column(name = "followed_id", nullable = false, insertable = false, updatable = false)
    public int getFollowedId() {
        return followedId;
    }

    public void setFollowedId(int followedId) {
        this.followedId = followedId;
    }

    @Basic
    @Column(name = "date", nullable = true)
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

        Follows follows = (Follows) o;

        if (id != follows.id) return false;
        if (followerId != follows.followerId) return false;
        if (followedId != follows.followedId) return false;
        if (date != null ? !date.equals(follows.date) : follows.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + followerId;
        result = 31 * result + followedId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false)
    public User getFollower() {
        return follower;
    }

    public void setFollower(User userByFollowerId) {
        this.follower = userByFollowerId;
    }

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "id", nullable = false)
    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User userByFollowedId) {
        this.followed = userByFollowedId;
    }
}
