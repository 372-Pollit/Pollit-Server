package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sent_message", schema = "public", catalog = "pollit")
public class SentMessage {
    private int id;
    private int receiverId;
    private int senderId;
    private String content;
    private String topic;
    private Timestamp date;
    private User receiver;
    private User sender;

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
    @Column(name = "receiver_id", nullable = false, insertable = false, updatable = false)
    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Basic
    @Column(name = "sender_id", nullable = false, insertable = false, updatable = false)
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "topic", nullable = true, length = 100)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

        SentMessage that = (SentMessage) o;

        if (id != that.id) return false;
        if (receiverId != that.receiverId) return false;
        if (senderId != that.senderId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + receiverId;
        result = 31 * result + senderId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User userByReceiverId) {
        this.receiver = userByReceiverId;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    public User getSender() {
        return sender;
    }

    public void setSender(User userBySenderId) {
        this.sender = userBySenderId;
    }
}
