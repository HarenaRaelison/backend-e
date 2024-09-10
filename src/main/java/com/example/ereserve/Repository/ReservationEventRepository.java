package com.example.ereserve.Repository;

import com.example.ereserve.Entity.ReservationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationEventRepository extends JpaRepository<ReservationEvent,Long> {

}
