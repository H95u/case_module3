package com.example.case_study_module3.model;


import java.time.LocalDateTime;

public class Booking {
    private int id;
    private User user;
    private Partner partner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Options option;

    public Booking() {
    }

    public Booking(int id, User user, Partner partner, LocalDateTime startTime, LocalDateTime endTime,Options option) {
        this.id = id;
        this.user = user;
        this.partner = partner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.option = option;
    }

    public Booking(User user, Partner partner, LocalDateTime startTime, LocalDateTime endTime,Options option) {
        this.user = user;
        this.partner = partner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.option = option;
    }

    public Options getOption() {
        return option;
    }

    public void setOption(Options option) {
        this.option = option;
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
