package com.example.ThrillZone.Park.Rides;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Ride_Entity_Repo extends JpaRepository<Ride_Entity,Long> {

    @Query("SELECT COUNT(r) FROM Ride_Entity r WHERE r.is_available = true")
    long countAvailableRides();

    @Query("SELECT COUNT(r) FROM Ride_Entity r WHERE r.is_available = false")
    long countUnavailableRides();
}