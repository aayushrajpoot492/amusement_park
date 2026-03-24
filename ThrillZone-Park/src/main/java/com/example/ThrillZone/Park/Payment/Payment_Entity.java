package com.example.ThrillZone.Park.Payment;

import jakarta.persistence.*;
import com.example.ThrillZone.Park.Booking.Booking_Entity;
import java.time.LocalDateTime;

@Entity
public class Payment_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pay_id;

    String p_mode,p_type,status;
    LocalDateTime p_date,p_time;
    double total_amount;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking_Entity bookings;

    public Payment_Entity() {
    }

    public Payment_Entity(Long pay_id, String p_mode, String p_type, String status, LocalDateTime p_date, LocalDateTime p_time, double total_amount) {
        this.pay_id = pay_id;
        this.p_mode = p_mode;
        this.p_type = p_type;
        this.status = status;
        this.p_date = p_date;
        this.p_time = p_time;
        this.total_amount = total_amount;
    }

    public Long getPay_id() {
        return pay_id;
    }

    public void setPay_id(Long pay_id) {
        this.pay_id = pay_id;
    }

    public String getP_mode() {
        return p_mode;
    }

    public void setP_mode(String p_mode) {
        this.p_mode = p_mode;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getP_date() {
        return p_date;
    }

    public void setP_date(LocalDateTime p_date) {
        this.p_date = p_date;
    }

    public LocalDateTime getP_time() {
        return p_time;
    }

    public void setP_time(LocalDateTime p_time) {
        this.p_time = p_time;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
}
