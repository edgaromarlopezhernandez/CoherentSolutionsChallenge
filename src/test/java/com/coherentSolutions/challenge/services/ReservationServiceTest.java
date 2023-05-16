package com.coherentSolutions.challenge.services;

import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;
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
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
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

    @DisplayName("Create a reservation")
    @Test
    public void createReservationTest() {
        /*ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
        reservationRequest.setId(null);
        Mockito.when(dataFormatter.formatReservationBeforePersistToCreateANewRecord(any(ReservationRequest.class))).thenReturn(ReservationStubs.getReservation());
        Mockito.when(converter.fromEntity(any(Reservation.class))).thenReturn(ReservationStubs.getReservationResponse());
        Mockito.when(reservationDAO.save(any(Reservation.class))).thenReturn(ReservationStubs.getReservation());
        when(reservationDAO.save(Mockito.any(Reservation.class))).thenAnswer(invocation -> {
            Reservation savedReservation = invocation.getArgument(0);
            savedReservation.setId(1); // Simulating the ID generated during save
            return savedReservation;
        });
        Mockito.when(reservationDAO.save(Mockito.any(Reservation.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        ReservationResponse reservationResponse = service.createReservation(reservationRequest);
        Assertions.assertEquals(reservationRequest.getClientFullName(), reservationResponse.getClientFullName());*/
    }
}