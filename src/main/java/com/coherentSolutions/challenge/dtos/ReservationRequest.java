package com.coherentSolutions.challenge.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ReservationRequest {

    private String id;
    private String clientFullName;
    private String roomNumber;
    private List<String> reservationDates;
}
