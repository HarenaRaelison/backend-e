package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "reservations_trans")
public class ReservationTrans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trans_id", nullable = false)
    private TransportReservation transportReservation;

    @ManyToOne
    @JoinColumn(name = "type_billet_trans_id", nullable = false)
    private Type_Billet_Trans typeBilletTrans;

    @Column(name = "nombre_places_reservees", nullable = false)
    private int nombrePlacesReservees;

    @Column(name = "montant_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private boolean status;

    @Column(length = 30, nullable = false)
    private String statut = "en attente";

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransportReservation getTransportReservation() {
        return transportReservation;
    }

    public void setTransportReservation(TransportReservation transportReservation) {
        this.transportReservation = transportReservation;
    }

    public Type_Billet_Trans getTypeBilletTrans() {
        return typeBilletTrans;
    }

    public void setTypeBilletTrans(Type_Billet_Trans typeBilletTrans) {
        this.typeBilletTrans = typeBilletTrans;
    }

    public int getNombrePlacesReservees() {
        return nombrePlacesReservees;
    }

    public void setNombrePlacesReservees(int nombrePlacesReservees) {
        this.nombrePlacesReservees = nombrePlacesReservees;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
