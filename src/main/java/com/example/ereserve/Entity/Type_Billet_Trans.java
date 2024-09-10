package com.example.ereserve.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_billet_trans")
public class Type_Billet_Trans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "prix", nullable = false)
    private double prix;
    @Column(name = "nombre", nullable = false)
    private double nombre;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private TransportReservation transportReservation;

    // Getters and Setters
    // ...

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

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }

    public TransportReservation getTransportReservation() {
        return transportReservation;
    }

    public void setTransportReservation(TransportReservation transportReservation) {
        this.transportReservation = transportReservation;
    }
}