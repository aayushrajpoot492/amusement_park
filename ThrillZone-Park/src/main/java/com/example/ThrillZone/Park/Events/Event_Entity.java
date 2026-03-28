package com.example.ThrillZone.Park.Events;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Event_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long e_id;

    String e_name,e_desc;
    int age_limit;
    double e_price;
    boolean is_available;
    LocalDate e_date;
    LocalTime e_time;
    @Lob
    @Column(columnDefinition = "bytea")
    byte[] e_image;


    public Event_Entity() {
    }

    public Event_Entity(Long e_id, String e_name, String e_desc, int age_limit, double e_price, boolean is_available, LocalDate e_date, LocalTime e_time, byte[] e_image) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_desc = e_desc;
        this.age_limit = age_limit;
        this.e_price = e_price;
        this.is_available = is_available;
        this.e_date = e_date;
        this.e_time = e_time;
        this.e_image = e_image;
    }


    public Long getE_id() {
        return e_id;
    }

    public void setE_id(Long e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_desc() {
        return e_desc;
    }

    public void setE_desc(String e_desc) {
        this.e_desc = e_desc;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public double getE_price() {
        return e_price;
    }

    public void setE_price(double e_price) {
        this.e_price = e_price;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public LocalDate getE_date() {
        return e_date;
    }

    public void setE_date(LocalDate e_date) {
        this.e_date = e_date;
    }

    public LocalTime getE_time() {
        return e_time;
    }

    public void setE_time(LocalTime e_time) {
        this.e_time = e_time;
    }

    public byte[] getE_image() {
        return e_image;
    }

    public void setE_image(byte[] e_image) {
        this.e_image = e_image;
    }
}

