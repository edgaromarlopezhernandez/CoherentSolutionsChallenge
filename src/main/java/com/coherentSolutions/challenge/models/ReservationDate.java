package com.coherentSolutions.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESERVATION_DATES")
@Entity
public class ReservationDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "RESERVATION_DATE", nullable = false)
    private LocalDate reservationDate;

    @ManyToOne
    @JoinColumn(name = "FK_RESERVATION_ID")
    //@PrimaryKeyJoinColumn
    private Reservation reservation;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReservationDate)) {
            return false;
        }
        ReservationDate c = (ReservationDate) o;

        return (id == c.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
