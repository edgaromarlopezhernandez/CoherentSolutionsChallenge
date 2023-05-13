package com.coherentSolutions.challenge.util;

import com.coherentSolutions.challenge.dtos.ReservationRequest;

import java.util.Arrays;

public class ReservationRequestStubs {

    public static ReservationRequest getReservationRequestStub() {
        return ReservationRequest.builder()
                .id("77")
                .clientFullName("Edgar Omar Lopez")
                .roomNumber("303")
                .reservationDates(Arrays.asList("2023-05-10", "2023-05-11", "2023-05-12"))
                .build();
    }
}
