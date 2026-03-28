package com.example.ThrillZone.Park.Review;

import jakarta.persistence.*;
import com.example.ThrillZone.Park.Master.User_Master;
import com.example.ThrillZone.Park.Rides.Ride_Entity;

@Entity
public class Review_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long review_id;

    int rating;
    String comment,best_feature;;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "m_id", nullable = false)
     User_Master user;

    public Review_Entity() {
    }

    public Review_Entity(Long review_id, int rating, String comment, String best_feature, User_Master user) {
        this.review_id = review_id;
        this.rating = rating;
        this.comment = comment;
        this.best_feature = best_feature;
        this.user = user;
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBest_feature() {
        return best_feature;
    }

    public void setBest_feature(String best_feature) {
        this.best_feature = best_feature;
    }

    public User_Master getUser() {
        return user;
    }

    public void setUser(User_Master user) {
        this.user = user;
    }
}

