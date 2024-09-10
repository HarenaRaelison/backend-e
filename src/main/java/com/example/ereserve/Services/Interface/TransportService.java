package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.TransportReservation;

public interface TransportService {
    TransportReservation addTransport(TransportReservation transportReservation);
    TransportReservation updateTransport(Long idTrans,TransportReservation transportReservation);
    String deleteTransport(Long idTrans);
}
