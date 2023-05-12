package com.coherentSolutions.challenge.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "Request DTO to create a new hotel reservation. Use this DTO as a request body (JSON) in POST and PUST methods in RESERVATIONS API. NOTE: All fields are String values to do proper validations.")
@Data
public class ReservationRequest implements Serializable {

    @Schema(name = "id", description = "The ID is a field that should only be filled in case of data update with its respective HTTP POST method, otherwise is not required")
    private String id;
    @Schema(name = "clientFullName", description = "Full name of the client making the hotel reservation. Numbers not allowed.")
    private String clientFullName;
    @Schema(name = "roomNumber", description = "Room number. Chars not allowed.")
    private String roomNumber;
    @Schema(name = "reservationDates", description = "Reservation dates for the hotel room. It is a list in the format yyyy-MM-dd.")
    private List<String> reservationDates;
}