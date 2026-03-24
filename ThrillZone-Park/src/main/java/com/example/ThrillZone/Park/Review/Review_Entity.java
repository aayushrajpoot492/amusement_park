package com.example.ThrillZone.Park.Review;

import jakarta.persistence.*;

@Entity
public class Review_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long review_id;

    int rating;
    String comment;

    public Review_Entity() {
    }

    public Review_Entity(Long review_id, int rating, String comment) {
        this.review_id = review_id;
        this.rating = rating;
        this.comment = comment;
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
}

