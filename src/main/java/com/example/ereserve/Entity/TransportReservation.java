package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transport_reservation")
public class TransportReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "lieuDepart", nullable = false)
    private String lieuDepart;

    @Column(name = "lieuArriver", nullable = false)
    private String lieuArriver;

    @Column(name = "dateDepart", nullable = false)
    private LocalDate dateDepart;

    @Column(name = "heureDepart", nullable = false)
    private LocalTime heureDepart;

    @Column(name = "nombrePlace", nullable = false)
    private int nombrePlace;

    @Column(nullable = false)
    private int prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false)
    private CategorieTrans categorie;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceReservation serviceId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArriver() {
        return lieuArriver;
    }

    public void setLieuArriver(String lieuArriver) {
        this.lieuArriver = lieuArriver;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public CategorieTrans getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieTrans categorie) {
        this.categorie = categorie;
    }

    public ServiceReservation getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServiceReservation serviceId) {
        this.serviceId = serviceId;
    }
}

enum CategorieTrans {
    BUS,
    TAXI_BROUSSE,
    TRAIN
}
