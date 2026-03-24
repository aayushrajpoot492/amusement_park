package com.example.ThrillZone.Park.Rides;

import jakarta.persistence.*;

@Entity
public class Ride_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ride_id;

    String name,description;
    int capacity;
    double r_price;
    Boolean is_available;

    public Ride_Entity() {
    }

    public Ride_Entity(Long ride_id, String name, String description, int price, Boolean is_available, int capacity) {
        this.ride_id = ride_id;
        this.name = name;
        this.description = description;
        this.r_price = r_price;
        this.is_available = is_available;
        this.capacity = capacity;
    }

    public Long getRide_id() {
        return ride_id;
    }

    public void setRide_id(Long ride_id) {
        this.ride_id = ride_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return r_price;
    }

    public void setPrice(double r_price) {
        this.r_price = r_price;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

