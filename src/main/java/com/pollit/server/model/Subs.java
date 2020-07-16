package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subs", schema = "public")
public class Subs {
    private int id;
    private int userId;
    private int categoryId;
    private Timestamp date;
    private User user;
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
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

        Subs subs = (Subs) o;

        if (id != subs.id) return false;
        if (userId != subs.userId) return false;
        if (categoryId != subs.categoryId) return false;
        if (date != null ? !date.equals(subs.date) : subs.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + categoryId;
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
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryByCategoryId) {
        this.category = categoryByCategoryId;
    }
}
