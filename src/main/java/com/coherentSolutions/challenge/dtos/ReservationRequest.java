package com.coherentSolutions.challenge.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "Request DTO to create a new hotel reservation. Use this DTO as a request body (JSON) in POST and PUT methods in RESERVATIONS API. NOTE: All fields are String values to do proper validations.")
@Data
@Builder
public class ReservationRequest implements Serializable {

    @Schema(name = "id", description = "The ID is a field that should only be filled in case of data update with its respective HTTP POST method, otherwise is not required", example = "77")
    private String id;
    @Schema(name = "clientFullName", description = "Full name of the client making the hotel reservation. Numbers not allowed.", example = "Keanu Reeves")
    private String clientFullName;
    @Schema(name = "roomNumber", description = "Room number. Chars not allowed.", example = "707")
    private String roomNumber;
    @Schema(name = "reservationDates", description = "Reservation dates for the hotel room. It is a list in the format yyyy-MM-dd.", example = "2023-05-10")
    private List<String> reservationDates;
}