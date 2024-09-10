package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "reservations_event")
public class ReservationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "evenement_id", nullable = false)
    private EvenementReservation evenementReservation;

    @ManyToOne
    @JoinColumn(name = "type_billet_id", nullable = false)
    private Type_Billet_Event typeBilletEvent;

    @Column(name = "nombre_places_reservees", nullable = false)
    private int nombrePlacesReservees;

    @Column(name = "montant_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private boolean status;

    @Column(length = 30, nullable = false)
    private String statut = "en attente";

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

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

    public EvenementReservation getEvenementReservation() {
        return evenementReservation;
    }

    public void setEvenementReservation(EvenementReservation evenementReservation) {
        this.evenementReservation = evenementReservation;
    }

    public Type_Billet_Event getTypeBilletEvent() {
        return typeBilletEvent;
    }

    public void setTypeBilletEvent(Type_Billet_Event typeBilletEvent) {
        this.typeBilletEvent = typeBilletEvent;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
