package com.example.ereserve.Repository;

import com.example.ereserve.Entity.EvenementReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<EvenementReservation,Long> {

}
