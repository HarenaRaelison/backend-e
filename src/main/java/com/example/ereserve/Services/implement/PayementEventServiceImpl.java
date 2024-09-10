package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.PaymentEvent;
import com.example.ereserve.Repository.PayementEventRepository;
import com.example.ereserve.Services.Interface.PayementEventService;
import org.springframework.stereotype.Service;

@Service
public class PayementEventServiceImpl implements PayementEventService {
    private final PayementEventRepository payementEventRepository;

    public PayementEventServiceImpl(PayementEventRepository payementEventRepository) {
        this.payementEventRepository = payementEventRepository;
    }

    @Override
    public PaymentEvent AddPayment(PaymentEvent paymentEvent) {
        return payementEventRepository.save(paymentEvent);
    }

    @Override
    public PaymentEvent updatePayment(Long IdPayment, PaymentEvent paymentEvent) {
        return payementEventRepository.findById(IdPayment)
                .map(
                        p -> {
                            p.setDevise(p.getDevise());
                            p.setMontant(p.getMontant());
                            p.setModePaiement(p.getModePaiement());
                            p.setPayerEmail(p.getPayerEmail());
                            p.setPaypalFee(p.getPaypalFee());
                            p.setPayerId(p.getPayerId());
                            p.setPaypalTransactionId(p.getPaypalTransactionId());
                            p.setPaypalStatus(p.getPaypalStatus());
                            p.setReservationEvent(p.getReservationEvent());

                                return payementEventRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Update Error"));
    }

    @Override
    public String deletePayment(Long idPayment) {
        payementEventRepository.deleteById(idPayment);
     return "payment deleted !";
    }
}
