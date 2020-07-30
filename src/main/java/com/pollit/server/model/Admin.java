package com.pollit.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin", schema = "public")
public class Admin {
    private int userId;
    private String tckNo;
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
    public String getTckNo() {
        return tckNo;
    }

    public void setTckNo(String tckNo) {
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
        if (!tckNo.equals(admin.tckNo)) return false;
        if (phoneNumber != null ? !phoneNumber.equals(admin.phoneNumber) : admin.phoneNumber != null) return false;
        if (address != null ? !address.equals(admin.address) : admin.address != null) return false;
        if (startDate != null ? !startDate.equals(admin.startDate) : admin.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getTckNo() != null ? getTckNo().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
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
