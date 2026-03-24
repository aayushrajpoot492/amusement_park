package com.example.ThrillZone.Park.Booking;

import com.example.ThrillZone.Park.Master.User_Master;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long booking_id;

    String b_name,category,status;
    LocalDateTime date,time;
    int  tickets_no;
    double price_per_ticket;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "m_id",nullable = false)
    private User_Master user;

    public Booking_Entity() {
    }

    public Booking_Entity(Long booking_id, String b_name, String category, String status, LocalDateTime date, LocalDateTime time, int tickets_no, double price_per_ticket, User_Master user) {
        this.booking_id = booking_id;
        this.b_name = b_name;
        this.category = category;
        this.status = status;
        this.date = date;
        this.time = time;
        this.tickets_no = tickets_no;
        this.price_per_ticket = price_per_ticket;
        this.user = user;
    }

    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTickets_no() {
        return tickets_no;
    }

    public void setTickets_no(int tickets_no) {
        this.tickets_no = tickets_no;
    }

    public double getPrice_per_ticket() {
        return price_per_ticket;
    }

    public void setPrice_per_ticket(double price_per_ticket) {
        this.price_per_ticket = price_per_ticket;
    }

    public User_Master getUser() {
        return user;
    }

    public void setUser(User_Master user) {
        this.user = user;
    }
}

