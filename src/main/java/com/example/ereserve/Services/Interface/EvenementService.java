package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.EvenementReservation;

public interface EvenementService {
    EvenementReservation addEvent(EvenementReservation evenementReservation);
    EvenementReservation updateEvent(Long idEvent,EvenementReservation evenementReservation);
    String deleteEvent(Long idEvent);
}
