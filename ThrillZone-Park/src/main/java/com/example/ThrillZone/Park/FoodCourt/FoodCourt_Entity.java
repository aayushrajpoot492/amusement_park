package com.example.ThrillZone.Park.FoodCourt;

import jakarta.persistence.*;

@Entity
public class FoodCourt_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long food_id;

    String f_name,category,food_desc;
    double f_price;
    Boolean is_available;
    @Lob
    @Column(columnDefinition = "bytea")
     byte[] f_image;

    public FoodCourt_Entity() {
    }

    public FoodCourt_Entity(Long food_id, String f_name, String category, String food_desc, double f_price, Boolean is_available, byte[] f_image) {
        this.food_id = food_id;
        this.f_name = f_name;
        this.category = category;
        this.food_desc = food_desc;
        this.f_price = f_price;
        this.is_available = is_available;
        this.f_image = f_image;
    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return food_desc;
    }

    public void setDesc(String food_desc) {
        this.food_desc = food_desc;
    }

    public double getF_price() {
        return f_price;
    }

    public void setF_price(double f_price) {
        this.f_price = f_price;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public byte[] getF_image() {
        return f_image;
    }

    public void setF_image(byte[] f_image) {
        this.f_image = f_image;
    }
}

