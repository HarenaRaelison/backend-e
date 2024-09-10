package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.Type_Billet_Event;


public interface billetEventService {
    Type_Billet_Event newBillet(Type_Billet_Event typeBilletEvent);
    Type_Billet_Event updateBillet(Long idBillet, Type_Billet_Event typeBilletEvent);
}
