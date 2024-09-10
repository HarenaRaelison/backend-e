package com.example.ereserve.Repository;

import com.example.ereserve.Entity.Type_Billet_Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface billetEventRepository extends JpaRepository<Type_Billet_Event,Long> {
}
