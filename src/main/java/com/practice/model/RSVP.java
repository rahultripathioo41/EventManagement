package com.practice.model;

import jakarta.persistence.*;

@Entity
public class RSVP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String status; // Going, Maybe, Decline

    // Many RSVPs belong to one User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many RSVPs belong to one Event
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}
