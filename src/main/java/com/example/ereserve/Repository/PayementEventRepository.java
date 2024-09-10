package com.example.ereserve.Repository;

import com.example.ereserve.Entity.PaymentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayementEventRepository extends JpaRepository<PaymentEvent,Long> {
}
