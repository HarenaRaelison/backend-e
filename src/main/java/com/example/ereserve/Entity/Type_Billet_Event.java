package com.example.ereserve.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_billet_event")
public class Type_Billet_Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "prix", nullable = false)
    private double prix;
    @Column(name = "nombre", nullable = false)
    private int nombre;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private EvenementReservation evenementReservation;

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public EvenementReservation getEvenementReservation() {
        return evenementReservation;
    }

    public void setEvenementReservation(EvenementReservation evenementReservation) {
        this.evenementReservation = evenementReservation;
    }
}
