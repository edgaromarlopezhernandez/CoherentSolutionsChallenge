package com.coherentSolutions.challenge.repositories;

import com.coherentSolutions.challenge.models.ReservationDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDateDAO extends JpaRepository<ReservationDate, Integer> {
}
