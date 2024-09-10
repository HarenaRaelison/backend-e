package com.example.ereserve.Services.Interface;


import com.example.ereserve.Entity.ReservationTrans;

public interface ReservationTransService {
    ReservationTrans reserveEvent(ReservationTrans reservationEvent);
    ReservationTrans UpdateReserveEvent(Long idReservation, ReservationTrans reservationEvent);
    String deleteReservation(Long idReservationTrans);
}
