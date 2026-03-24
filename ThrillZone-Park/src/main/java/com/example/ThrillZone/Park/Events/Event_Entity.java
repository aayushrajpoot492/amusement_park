package com.example.ThrillZone.Park.Events;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long e_id;

    String e_name,e_desc;
    int age_limit;
    double e_price;
    boolean is_available;

    public Event_Entity() {
    }

    public Event_Entity(Long e_id, String e_name, String e_desc, int age_limit, double e_price, boolean is_available) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_desc = e_desc;
        this.age_limit = age_limit;
        this.e_price = e_price;
        this.is_available = is_available;
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
}

