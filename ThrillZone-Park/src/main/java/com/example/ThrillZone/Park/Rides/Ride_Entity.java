package com.example.ThrillZone.Park.Rides;

import jakarta.persistence.*;

@Entity
public class Ride_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Long ride_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "min_age")
    private int min_age;

    @Column(name = "r_price")
    private double r_price;

    @Column(name = "is_available")
    private Boolean is_available;
    @Lob
    @Column(columnDefinition = "bytea")
    byte[] r_image;

    public Ride_Entity() {
    }

    public Ride_Entity(Long ride_id, String name, String description, int capacity, int min_age, double r_price, Boolean is_available, byte[] r_image) {
        this.ride_id = ride_id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.min_age = min_age;
        this.r_price = r_price;
        this.is_available = is_available;
        this.r_image = r_image;
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

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public double getR_price() {
        return r_price;
    }

    public void setR_price(double r_price) {
        this.r_price = r_price;
    }

    public byte[] getR_image() {
        return r_image;
    }

    public void setR_image(byte[] r_image) {
        this.r_image = r_image;
    }

}

