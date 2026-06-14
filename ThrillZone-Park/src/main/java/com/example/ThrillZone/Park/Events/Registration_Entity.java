package com.example.ThrillZone.Park.Events;

import com.example.ThrillZone.Park.Payment.Payment_Entity;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Registration_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    private String name;
    private String email;
    private String phone;
    private int participants;
    private double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime regTime;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event_Entity event;

    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment_Entity payment;

    public Registration_Entity() {
    }

    public Long getRegistrationId() { return registrationId; }
    public void setRegistrationId(Long registrationId) { this.registrationId = registrationId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getParticipants() { return participants; }
    public void setParticipants(int participants) { this.participants = participants; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getRegDate() { return regDate; }
    public void setRegDate(LocalDate regDate) { this.regDate = regDate; }

    public LocalTime getRegTime() { return regTime; }
    public void setRegTime(LocalTime regTime) { this.regTime = regTime; }

    public Event_Entity getEvent() { return event; }
    public void setEvent(Event_Entity event) { this.event = event; }

    public Payment_Entity getPayment() { return payment; }
    public void setPayment(Payment_Entity payment) { this.payment = payment; }
}