package com.example.ereserve.Repository;

import com.example.ereserve.Entity.ServiceReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceReservation,Long> {
    
}
