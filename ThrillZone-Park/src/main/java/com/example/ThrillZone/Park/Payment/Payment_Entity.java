package com.example.ThrillZone.Park.Payment;

import jakarta.persistence.*;
import com.example.ThrillZone.Park.Rides.Booking_Entity;
import com.example.ThrillZone.Park.Events.Registration_Entity;
import java.time.LocalDateTime;

@Entity
public class Payment_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pay_id;

    private String p_mode;
    private String status;
    private double total_amount;
    private LocalDateTime payment_date_time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = true)
    private Booking_Entity booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id", nullable = true)
    private Registration_Entity registration;

    public Payment_Entity() {
    }

    public Long getPay_id() { return pay_id; }
    public void setPay_id(Long pay_id) { this.pay_id = pay_id; }

    public String getP_mode() { return p_mode; }
    public void setP_mode(String p_mode) { this.p_mode = p_mode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal_amount() { return total_amount; }
    public void setTotal_amount(double total_amount) { this.total_amount = total_amount; }

    public LocalDateTime getPayment_date_time() { return payment_date_time; }
    public void setPayment_date_time(LocalDateTime payment_date_time) { this.payment_date_time = payment_date_time; }

    public Booking_Entity getBooking() { return booking; }
    public void setBooking(Booking_Entity booking) { this.booking = booking; }

    public Registration_Entity getRegistration() { return registration; }
    public void setRegistration(Registration_Entity registration) { this.registration = registration; }
}