package com.example.ThrillZone.Park.Booking;

import com.example.ThrillZone.Park.Master.User_Master;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long booking_id;

    String status;
    LocalDateTime booking_date_time;
    int  total_tickets;;
    double total_amount;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "m_id",nullable = false)
     User_Master user;

    public Booking_Entity() {
    }

    public Booking_Entity(Long booking_id, String status, LocalDateTime booking_date_time, int total_tickets, double total_amount, User_Master user) {
        this.booking_id = booking_id;
        this.status = status;
        this.booking_date_time = booking_date_time;
        this.total_tickets = total_tickets;
        this.total_amount = total_amount;
        this.user = user;
    }

    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBooking_date_time() {
        return booking_date_time;
    }

    public void setBooking_date_time(LocalDateTime booking_date_time) {
        this.booking_date_time = booking_date_time;
    }

    public int getTotal_tickets() {
        return total_tickets;
    }

    public void setTotal_tickets(int total_tickets) {
        this.total_tickets = total_tickets;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public User_Master getUser() {
        return user;
    }

    public void setUser(User_Master user) {
        this.user = user;
    }
}

