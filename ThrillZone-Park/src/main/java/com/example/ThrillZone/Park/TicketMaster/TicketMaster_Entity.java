package com.example.ThrillZone.Park.TicketMaster;

import com.example.ThrillZone.Park.Booking.Booking_Entity;
import com.example.ThrillZone.Park.Events.Event_Entity;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity;
import jakarta.persistence.*;

@Entity
public class TicketMaster_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long t_id;

    String visitor_name;
    int visitor_age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", nullable = false)
     Booking_Entity booking;

    @ManyToOne
    @JoinColumn(name = "ride_id")
     Ride_Entity ride;

    @ManyToOne
    @JoinColumn(name = "event_id")
     Event_Entity event;

    @ManyToOne
    @JoinColumn(name = "food_id")
     FoodCourt_Entity food;

    public TicketMaster_Entity() {
    }

    public TicketMaster_Entity(Long t_id, String visitor_name, int visitor_age, Booking_Entity booking, Ride_Entity ride, Event_Entity event, FoodCourt_Entity food) {
        this.t_id = t_id;
        this.visitor_name = visitor_name;
        this.visitor_age = visitor_age;
        this.booking = booking;
        this.ride = ride;
        this.event = event;
        this.food = food;
    }

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public String getVisitor_name() {
        return visitor_name;
    }

    public void setVisitor_name(String visitor_name) {
        this.visitor_name = visitor_name;
    }

    public int getVisitor_age() {
        return visitor_age;
    }

    public void setVisitor_age(int visitor_age) {
        this.visitor_age = visitor_age;
    }

    public Booking_Entity getBooking() {
        return booking;
    }

    public void setBooking(Booking_Entity booking) {
        this.booking = booking;
    }

    public Ride_Entity getRide() {
        return ride;
    }

    public void setRide(Ride_Entity ride) {
        this.ride = ride;
    }

    public Event_Entity getEvent() {
        return event;
    }

    public void setEvent(Event_Entity event) {
        this.event = event;
    }

    public FoodCourt_Entity getFood() {
        return food;
    }

    public void setFood(FoodCourt_Entity food) {
        this.food = food;
    }
}
