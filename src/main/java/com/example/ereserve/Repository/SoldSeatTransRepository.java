package com.example.ereserve.Repository;

import com.example.ereserve.Entity.SoldSeatTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoldSeatTransRepository extends JpaRepository<SoldSeatTrans, Long> {

    // Retrieve a single SoldSeatTrans record by reservation ID
    @Query("SELECT s FROM SoldSeatTrans s WHERE s.reservationTrans.id = :reservationTransId")
    Optional<SoldSeatTrans> findSoldSeatsByReservationId(@Param("reservationTransId") Long reservationTransId);

    // Retrieve sold seat numbers for a specific reservation ID as a list of lists
    @Query("SELECT s.seatNumbers FROM SoldSeatTrans s WHERE s.reservationTrans.id = :reservationTransId")
    List<List<Integer>> findSeatNumbersByReservationId(@Param("reservationTransId") Long reservationTransId);
}
