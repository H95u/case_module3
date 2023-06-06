package com.example.case_study_module3.model;


import java.time.LocalDateTime;

public class Booking {
    private int id;
    private User user;
    private Partner partner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking() {
    }

    public Booking(int id, User user, Partner partner, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.user = user;
        this.partner = partner;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Booking(int id, LocalDateTime startTime, LocalDateTime endTime) {
    }

    public Booking(int id, int userId, int partnerId, LocalDateTime startTime, LocalDateTime endTime) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", partner=" + partner +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
