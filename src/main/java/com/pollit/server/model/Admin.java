package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin", schema = "public")
public class Admin {
    private int userId;
    private int tckNo;
    private String phoneNumber;
    private String address;
    private Timestamp startDate;
    private User user;

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tck_no", nullable = false)
    public int getTckNo() {
        return tckNo;
    }

    public void setTckNo(int tckNo) {
        this.tckNo = tckNo;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (userId != admin.userId) return false;
        if (tckNo != admin.tckNo) return false;
        if (phoneNumber != null ? !phoneNumber.equals(admin.phoneNumber) : admin.phoneNumber != null) return false;
        if (address != null ? !address.equals(admin.address) : admin.address != null) return false;
        if (startDate != null ? !startDate.equals(admin.startDate) : admin.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + tckNo;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }
}
