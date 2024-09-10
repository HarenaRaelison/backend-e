package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transport_reservation")
public class  TransportReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "lieu_depart", nullable = false)
    private String lieuDepart;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "lieu_arriver", nullable = false)
    private String lieuArriver;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_depart", nullable = false)
    private Date dateDepart;

    @Temporal(TemporalType.TIME)
    @Column(name = "heure_depart", nullable = false)
    private Date heureDepart;

    @Column(name = "nombre_place", nullable = false)
    private int nombrePlace;

    @Column(nullable = false)
    private int prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false)
    private CategorieTrans categorie;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceReservation serviceReservation;

    @OneToMany(mappedBy = "transportReservation")
    private List<Type_Billet_Trans> typeBillets;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLieuArriver() {
        return lieuArriver;
    }

    public void setLieuArriver(String lieuArriver) {
        this.lieuArriver = lieuArriver;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
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

    public ServiceReservation getServiceReservation() {
        return serviceReservation;
    }

    public void setServiceReservation(ServiceReservation serviceReservation) {
        this.serviceReservation = serviceReservation;
    }

    public List<Type_Billet_Trans> getTypeBillets() {
        return typeBillets;
    }

    public void setTypeBillets(List<Type_Billet_Trans> typeBillets) {
        this.typeBillets = typeBillets;
    }
}

enum CategorieTrans {
    BUS,
    TAXI_BROUSSE,
    TRAIN
}