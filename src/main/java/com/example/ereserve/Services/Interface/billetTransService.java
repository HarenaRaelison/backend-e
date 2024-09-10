package com.example.ereserve.Services.Interface;


import com.example.ereserve.Entity.Type_Billet_Event;
import com.example.ereserve.Entity.Type_Billet_Trans;

public interface billetTransService {
    Type_Billet_Trans newBillet(Type_Billet_Trans typeBilletTrans);
    Type_Billet_Trans updateBillet(Long idBillet, Type_Billet_Trans typeBilletTrans);
}
