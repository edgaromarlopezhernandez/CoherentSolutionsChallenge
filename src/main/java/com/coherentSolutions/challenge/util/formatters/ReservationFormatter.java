package com.coherentSolutions.challenge.util.formatters;

import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.models.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationFormatter {

    public Reservation formatReservationBeforePersistToCreateANewRecord(ReservationRequest reservationRequest) {
        Reservation formattedReservation = new Reservation();
        formattedReservation.setClientFullName(reservationRequest.getClientFullName().trim().toUpperCase());
        formattedReservation.setRoomNumber(Integer.valueOf(reservationRequest.getRoomNumber()));
        return formattedReservation;
    }

    public Reservation formatReservationBeforePersistToUpdateARecord(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        if(reservationRequest.getClientFullName() != null)
            reservation.setClientFullName(reservationRequest.getClientFullName().trim().toUpperCase());
        if(reservationRequest.getRoomNumber() != null)
            reservation.setRoomNumber(Integer.valueOf(reservationRequest.getRoomNumber()));
        return reservation;
    }
}
