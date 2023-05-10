package com.coherentSolutions.challenge.dtos;

import com.coherentSolutions.challenge.models.ReservationDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationResponse {
    private Integer id;
    private String clientFullName;
    private Integer roomNumber;
    private List<String> reservationDates;
}
