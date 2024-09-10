package com.example.ereserve.Repository;

import com.example.ereserve.Entity.TransportReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<TransportReservation,Long> {
}
