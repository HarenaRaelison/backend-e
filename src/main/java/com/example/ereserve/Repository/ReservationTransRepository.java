package com.example.ereserve.Repository;

import com.example.ereserve.Entity.ReservationTrans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTransRepository extends JpaRepository<ReservationTrans,Long> {
}
