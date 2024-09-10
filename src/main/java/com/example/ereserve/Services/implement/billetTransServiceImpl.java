package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.Type_Billet_Event;
import com.example.ereserve.Entity.Type_Billet_Trans;
import com.example.ereserve.Repository.billetTransRepository;
import com.example.ereserve.Services.Interface.billetTransService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class billetTransServiceImpl implements billetTransService {
    private final billetTransRepository BilletTransRepository;

    @Override
    public Type_Billet_Trans newBillet(Type_Billet_Trans typeBilletTrans) {
        return BilletTransRepository.save(typeBilletTrans);
    }

    @Override
    public Type_Billet_Trans updateBillet(Long idBillet, Type_Billet_Trans typeBilletTrans) {
        return null;
    }
}
