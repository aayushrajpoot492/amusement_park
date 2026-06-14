package com.example.ThrillZone.Park.Rides;

import com.example.ThrillZone.Park.Payment.Payment_Entity; // Import payment entity
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Booking_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride_Entity ride;

    private String customer_name;
    private String customer_phone;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate booking_date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime booking_time;

    private int total_tickets;
    private double total_amount;
    private String payment_method;
    private String payment_status;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment_Entity payment;

    public Booking_Entity() {
    }

    public Long getBooking_id() { return booking_id; }
    public void setBooking_id(Long booking_id) { this.booking_id = booking_id; }

    public Ride_Entity getRide() { return ride; }
    public void setRide(Ride_Entity ride) { this.ride = ride; }

    public String getCustomer_name() { return customer_name; }
    public void setCustomer_name(String customer_name) { this.customer_name = customer_name; }

    public String getCustomer_phone() { return customer_phone; }
    public void setCustomer_phone(String customer_phone) { this.customer_phone = customer_phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getBooking_date() { return booking_date; }
    public void setBooking_date(LocalDate booking_date) { this.booking_date = booking_date; }

    public LocalTime getBooking_time() { return booking_time; }
    public void setBooking_time(LocalTime booking_time) { this.booking_time = booking_time; }

    public int getTotal_tickets() { return total_tickets; }
    public void setTotal_tickets(int total_tickets) { this.total_tickets = total_tickets; }

    public double getTotal_amount() { return total_amount; }
    public void setTotal_amount(double total_amount) { this.total_amount = total_amount; }

    public String getPayment_method() { return payment_method; }
    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public String getPayment_status() { return payment_status; }
    public void setPayment_status(String payment_status) { this.payment_status = payment_status; }

    public Payment_Entity getPayment() { return payment; }
    public void setPayment(Payment_Entity payment) { this.payment = payment; }
}