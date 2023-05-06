package com.coherentSolutions.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESERVATIONS")
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CLIENT_FULL_NAME", nullable = false)
    private String clientFullName;
    @Column(name = "ROOM_NUMBER", nullable = false)
    private Integer roomNumber;
    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    private List<ReservationDate> reservationDates;

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
