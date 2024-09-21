package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.TransportReservation;

import java.util.List;

public interface TransportService {
    TransportReservation addTransport(TransportReservation transportReservation);
    TransportReservation updateTransport(Long idTrans,TransportReservation transportReservation);
    String deleteTransport(Long idTrans);
    List<TransportReservation> getAllTransports();
}
