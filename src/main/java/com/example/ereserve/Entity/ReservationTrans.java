package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @OneToMany(mappedBy = "reservationTrans", cascade = CascadeType.ALL)
    private List<SoldSeatTrans> soldSeats;


    @Column(nullable = false)
    private boolean status;





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




    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SoldSeatTrans> getSoldSeats() {
        return soldSeats;
    }

    public void setSoldSeats(List<SoldSeatTrans> soldSeats) {
        this.soldSeats = soldSeats;
    }
}
