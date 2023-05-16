package com.coherentSolutions.challenge.util;

import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;
import com.coherentSolutions.challenge.models.Reservation;
import com.coherentSolutions.challenge.models.ReservationDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationStubs {

    public static ReservationRequest getReservationRequestStub() {
        return ReservationRequest.builder()
                .id("77")
                .clientFullName("Edgar Omar Lopez")
                .roomNumber("303")
                .reservationDates(Arrays.asList("2023-05-10", "2023-05-11", "2023-05-12"))
                .build();
    }

    public static Reservation getReservation() {
        ReservationDate reservationDate = ReservationDate.builder()
                .id(22)
                .reservationDate(LocalDate.of(2023, 05, 10))
                .build();
        List<ReservationDate> reservationDateList = new ArrayList<>();
        reservationDateList.add(reservationDate);

        return Reservation.builder()
                .id(77)
                .clientFullName("Edgar Omar Lopez")
                .roomNumber(303)
                .reservationDates(reservationDateList)
                .build();
    }

    public static List<ReservationDate> getReservationDatesList() {
        List<ReservationDate> reservationDateList = new ArrayList<>();
        ReservationDate reservationDate = ReservationDate.builder()
                .id(22)
                .reservationDate(LocalDate.of(2023, 05, 10))
                .reservation(Reservation.builder()
                        .id(77)
                        .clientFullName("Edgar Omar Lopez")
                        .roomNumber(303)
                        .reservationDates(reservationDateList)
                        .build())
                .build();
        reservationDateList.add(reservationDate);
        return reservationDateList;
    }

    public static ReservationResponse getReservationResponse() {
        return ReservationResponse.builder()
                .id(77)
                .clientFullName("Edgar Omar Lopez")
                .roomNumber(303)
                .reservationDates(Arrays.asList("2023-05-10", "2023-05-11", "2023-05-12"))
                .build();
    }
}
