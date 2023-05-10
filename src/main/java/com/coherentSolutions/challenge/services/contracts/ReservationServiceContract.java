package com.coherentSolutions.challenge.services.contracts;

import com.coherentSolutions.challenge.dtos.PaginatedReservations;
import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;

import java.util.Optional;

public interface ReservationServiceContract {
    Optional<PaginatedReservations> findAllReservations(Integer pageNumber, Integer pageSize);
    ReservationResponse createReservation(ReservationRequest reservationRequest);
    Optional<ReservationResponse> updateReservation(ReservationRequest reservationRequest);
}
