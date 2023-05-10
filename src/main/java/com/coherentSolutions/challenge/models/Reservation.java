package com.coherentSolutions.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESERVATIONS")
@Entity
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CLIENT_FULL_NAME", nullable = false)
    private String clientFullName;
    @Column(name = "ROOM_NUMBER", nullable = false)
    private Integer roomNumber;
    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ReservationDate> reservationDates;

    public void addReservationDate(ReservationDate reservationDate) {
        if(this.reservationDates == null)
            this.reservationDates = new ArrayList<>();
        ReservationDate newReservationDate = ReservationDate.builder()
                .reservationDate(reservationDate.getReservationDate())
                .reservation(this)
                .build();
        reservationDates.add(newReservationDate);
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reservation)) {
            return false;
        }
        Reservation c = (Reservation) o;

        return (id == c.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
