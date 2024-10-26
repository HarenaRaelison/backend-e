package com.example.ereserve.Repository;

import com.example.ereserve.Entity.ReservationTrans;
import com.example.ereserve.Entity.TransportReservation;
import com.example.ereserve.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationTransRepository extends JpaRepository<ReservationTrans, Long> {

    @Query("SELECT r FROM ReservationTrans r WHERE r.user = :user AND r.transportReservation = :transportReservation")
    Optional<ReservationTrans> findByUserAndTransportReservation(
            @Param("user") User user,
            @Param("transportReservation") TransportReservation transportReservation
    );
}
