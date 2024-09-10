package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.ReservationEvent;

public interface ReservationEventService {
    ReservationEvent reserveEvent(ReservationEvent reservationEvent);
    ReservationEvent UpdateReserveEvent(Long idReservation, ReservationEvent reservationEvent);
    String deleteReservation(Long idReservation);
}
