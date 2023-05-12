package com.coherentSolutions.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaginatedReservations implements Serializable {
    public List<ReservationResponse> reservations;
    public PageInfo pageInfo;
}
