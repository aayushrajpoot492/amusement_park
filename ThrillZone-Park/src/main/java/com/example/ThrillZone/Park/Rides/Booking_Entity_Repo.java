package com.example.ThrillZone.Park.Rides;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Booking_Entity_Repo extends JpaRepository<Booking_Entity,Long> {
    @Query(value =
            "SELECT * FROM booking_entity ORDER BY booking_id ASC LIMIT 5",
            nativeQuery = true)
    List<Booking_Entity> getRecentBookings();}