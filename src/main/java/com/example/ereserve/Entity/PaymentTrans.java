package com.example.ereserve.Entity;

import com.example.ereserve.Entity.ReservationTrans;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payments_trans")
public class PaymentTrans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationTrans reservationTrans;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(nullable = false, length = 10)
    private String devise = "USD";

    @Column(name = "mode_paiement", nullable = false)
    private String modePaiement = "paypal";

    @Column(nullable = false, length = 20)
    private String statut = "en attente";

    @Column(name = "paypal_transaction_id")
    private String paypalTransactionId;

    @Column(name = "payer_id")
    private String payerId;

    @Column(name = "payer_email")
    private String payerEmail;

    @Column(name = "paypal_status")
    private String paypalStatus;

    @Column(name = "paypal_fee", precision = 10, scale = 2)
    private BigDecimal paypalFee;



    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationTrans getReservationTrans() {
        return reservationTrans;
    }

    public void setReservationTrans(ReservationTrans reservationTrans) {
        this.reservationTrans = reservationTrans;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getPaypalTransactionId() {
        return paypalTransactionId;
    }

    public void setPaypalTransactionId(String paypalTransactionId) {
        this.paypalTransactionId = paypalTransactionId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPaypalStatus() {
        return paypalStatus;
    }

    public void setPaypalStatus(String paypalStatus) {
        this.paypalStatus = paypalStatus;
    }

    public BigDecimal getPaypalFee() {
        return paypalFee;
    }

    public void setPaypalFee(BigDecimal paypalFee) {
        this.paypalFee = paypalFee;
    }
}
