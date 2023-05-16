package com.coherentSolutions.challenge.services;

import com.coherentSolutions.challenge.dtos.PageInfo;
import com.coherentSolutions.challenge.dtos.PaginatedReservations;
import com.coherentSolutions.challenge.models.Reservation;
import com.coherentSolutions.challenge.repositories.ReservationDAO;
import com.coherentSolutions.challenge.repositories.ReservationDateDAO;
import com.coherentSolutions.challenge.util.PageableHelper;
import com.coherentSolutions.challenge.util.ReservationStubs;
import com.coherentSolutions.challenge.util.converters.ReservationConverter;
import com.coherentSolutions.challenge.util.formatters.ReservationFormatter;
import com.coherentSolutions.challenge.util.validators.ReservationValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService service;

    @Mock
    private PageableHelper pageableHelper;

    @Mock
    private ReservationConverter converter;

    @Mock
    private ReservationValidator dataValidator;

    @Mock
    private ReservationFormatter dataFormatter;

    @Mock
    private ReservationDAO reservationDAO;

    @Mock
    private ReservationDateDAO reservationDateDAO;

    @DisplayName("Return all reservations Happy path:)!")
    @Test
    public void returnAllReservations_WhenDatabaseIsNotEmpty() {
        when(reservationDAO.count()).thenReturn(1L);
        when(pageableHelper.helper(anyInt(), anyInt(), anyLong())).thenReturn(PageInfo.builder()
                        .totalRecords(1L)
                        .totalPages(1L)
                        .pageSize(2)
                        .previousPage(1)
                        .currentPage(2)//Validate this
                        .nextPage(1)
                .build());
        when(reservationDAO.findAll(any(Pageable.class))).thenReturn(ReservationStubs.getListOfReservations());
        when(converter.fromEntity(any(Reservation.class))).thenReturn(ReservationStubs.getReservationResponse());
        Optional<PaginatedReservations> response = service.findAllReservations(1, 2);

        Assertions.assertNotNull(response);
    }

    @DisplayName("Return all reservations when db is empty")
    @Test
    public void returnAllReservations_WhenDatabaseIsEmpty() {
        when(reservationDAO.count()).thenReturn(0L);
        Optional<PaginatedReservations> response = service.findAllReservations(1, 2);
        Assertions.assertEquals(Optional.empty(), response);
    }

    @DisplayName("Create a reservation")
    @Test
    public void createReservationTest() {

    }
}