package com.example.ThrillZone.Park.Booking;

import com.example.ThrillZone.Park.Master.User_Master;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Booking_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long booking_id;
String ride_name;
    String customer_name;

    String customer_phone;
    String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate booking_date;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime booking_time;
    int  total_tickets;;
    double total_amount;
    String payment_method;

    String payment_status;

//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name = "m_id",nullable = false)
//     User_Master user;

    public Booking_Entity() {
    }

    public Booking_Entity(Long booking_id, String ride_name,String customer_name,String customer_phone,String status, LocalDate booking_date, LocalTime booking_time, int total_tickets, double total_amount,String payment_status,String payment_method) {
        this.booking_id = booking_id;
        this.ride_name=ride_name;
        this.customer_name=customer_name;
        this.customer_phone=customer_phone;
        this.status = status;
        this.booking_date= booking_date;
        this.booking_time=booking_time;
        this.total_tickets = total_tickets;
        this.total_amount = total_amount;
        this.payment_status=payment_status;
        this.payment_method=payment_method;
//        this.user = user;
    }

    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public String getRide_name() {
        return ride_name;
    }

    public void setRide_name(String ride_name) {
        this.ride_name = ride_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(LocalDate booking_date) {
        this.booking_date= booking_date;
    }

    public LocalTime getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(LocalTime booking_time) {
        this.booking_time = booking_time;
    }

    public int getTotal_tickets() {
        return total_tickets;
    }

    public void setTotal_tickets(int total_tickets) {
        this.total_tickets = total_tickets;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
//    public User_Master getUser() {
//        return user;
//    }
//
//    public void setUser(User_Master user) {
//        this.user = user;
//    }
}

