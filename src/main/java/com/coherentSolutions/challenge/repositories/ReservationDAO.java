package com.coherentSolutions.challenge.repositories;

import com.coherentSolutions.challenge.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
}
