package com.coherentSolutions.challenge.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(description = "Response DTO for a hotel reservation, including dates.")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationResponse implements Serializable {
    @Schema(name = "id", description = "Unique identifier of the hotel reservation.")
    private Integer id;
    @Schema(name = "clientFullName", description = "Full name of the client making the hotel reservation.")
    private String clientFullName;
    @Schema(name = "roomNumber", description = "Hotel room number.")
    private Integer roomNumber;
    @Schema(name = "reservationDates", description = "Reservation dates for the hotel room.")
    private List<String> reservationDates;
}
