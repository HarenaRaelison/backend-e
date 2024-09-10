package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.PaymentEvent;

public interface PayementEventService {
     PaymentEvent AddPayment(PaymentEvent paymentEvent);
     PaymentEvent updatePayment(Long IdPayment ,PaymentEvent paymentEvent);
     String deletePayment(Long idPayment);
}
