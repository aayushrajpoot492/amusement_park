package com.example.ThrillZone.Park.TicketMaster;

import com.example.ThrillZone.Park.Booking.Booking_Entity;
import jakarta.persistence.*;

@Entity
public class TicketMaster_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long t_id;

    String name;
    int age;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking_Entity booking;

    public TicketMaster_Entity() {
    }

    public TicketMaster_Entity(Long t_id, String name, int age, Booking_Entity booking) {
        this.t_id = t_id;
        this.name = name;
        this.age = age;
        this.booking = booking;
    }

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Booking_Entity getBooking() {
        return booking;
    }

    public void setBooking(Booking_Entity booking) {
        this.booking = booking;
    }
}
