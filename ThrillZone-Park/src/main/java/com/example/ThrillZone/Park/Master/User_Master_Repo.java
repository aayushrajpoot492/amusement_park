package com.example.ThrillZone.Park.Master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface User_Master_Repo extends JpaRepository<User_Master,Long> {
    @Query("SELECT COUNT(u) > 0 FROM User_Master u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) > 0 FROM User_Master u WHERE u.phone_no = :phoneNo")
    boolean existsByPhoneNo(@Param("phoneNo") Long phoneNo);

    Optional<User_Master> findByEmail(String email);
}