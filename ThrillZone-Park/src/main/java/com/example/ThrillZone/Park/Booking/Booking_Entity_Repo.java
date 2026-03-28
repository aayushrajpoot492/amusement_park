package com.example.ThrillZone.Park.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Booking_Entity_Repo extends JpaRepository<Booking_Entity,Long> {
}

