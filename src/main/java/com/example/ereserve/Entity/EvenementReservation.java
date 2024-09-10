package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evenement_reservation")
public class EvenementReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(name = "image_path")
    private String imagePath;
    @OneToMany(mappedBy = "evenementReservation")
    private List<Type_Billet_Event> typeBillets;
    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_event", nullable = false)
    private CategorieEvent categorieEvent;

    @Column(name = "lieu_depart", nullable = false)
    private String lieuDepart;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Temporal(TemporalType.TIME)
    @Column(name = "time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceReservation serviceReservation;


    // Getters et Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public CategorieEvent getCategorieEvent() {
        return categorieEvent;
    }

    public void setCategorieEvent(CategorieEvent categorieEvent) {
        this.categorieEvent = categorieEvent;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ServiceReservation getServiceReservation() {
        return serviceReservation;
    }

    public void setServiceReservation(ServiceReservation serviceReservation) {
        this.serviceReservation = serviceReservation;
    }


}

enum CategorieEvent {
    SPECTACLE,
    CONCERTS,
    FOIRES,
    SEMINAIRES,
    SPORTS,
    LOISIRS,
    CULTURE
}
