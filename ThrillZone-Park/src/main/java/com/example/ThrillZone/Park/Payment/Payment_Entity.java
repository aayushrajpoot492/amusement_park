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
    LocalDateTime payment_date_time;;
    double total_amount;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking_Entity bookings;

    public Payment_Entity() {
    }


}
