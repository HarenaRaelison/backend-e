package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.Type_Billet_Event;
import com.example.ereserve.Repository.billetEventRepository;
import com.example.ereserve.Services.Interface.billetEventService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class billetEventServiceImpl implements billetEventService {
    private final billetEventRepository billetEventRepository;

    @Override
    public Type_Billet_Event newBillet(Type_Billet_Event typeBilletEvent) {
        return billetEventRepository.save(typeBilletEvent);
    }

    @Override
    public Type_Billet_Event updateBillet(Long idBillet, Type_Billet_Event typeBilletEvent) {
        return null;
    }
}
