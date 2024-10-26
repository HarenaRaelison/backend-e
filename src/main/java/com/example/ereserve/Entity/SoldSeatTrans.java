package com.example.ereserve.Entity;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "sold_seat_trans")
public class SoldSeatTrans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_trans_id", nullable = false)
    private ReservationTrans reservationTrans;

    @Column(name = "seat_numbers", nullable = false)
    private String seatNumbers;  // Stocke les numéros de sièges comme une chaîne de caractères

    // Getters and Setters
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

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    // Convert seat numbers to List<Integer> for easier manipulation
    public List<Integer> getSeatNumbersAsList() {
        return Arrays.stream(seatNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void setSeatNumbersFromList(List<Integer> seatNumbers) {
        this.seatNumbers = seatNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
