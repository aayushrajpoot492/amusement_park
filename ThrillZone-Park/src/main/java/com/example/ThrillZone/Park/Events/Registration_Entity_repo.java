package com.example.ThrillZone.Park.Events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Registration_Entity_repo
        extends JpaRepository<Registration_Entity, Long> {
}
