package com.example.ereserve.Repository;

import com.example.ereserve.Entity.ServiceReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<ServiceReservation,Long> {
    @Query("SELECT s FROM ServiceReservation s WHERE s.user.id = :userId")
    ServiceReservation findServiceByUserId(@Param("userId") Long userId);
}
