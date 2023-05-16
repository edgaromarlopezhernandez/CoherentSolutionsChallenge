package com.coherentSolutions.challenge.util;

import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;
import com.coherentSolutions.challenge.models.Reservation;
import com.coherentSolutions.challenge.models.ReservationDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    public static Page<Reservation> getListOfReservations() {
        List<Reservation> reservations = new ArrayList<>();
        List<ReservationDate> reservationDates = new ArrayList<>();
        ReservationDate reservationDate = ReservationDate.builder()
                .id(22)
                .reservationDate(LocalDate.of(2023, 05, 10))
                .build();
        reservationDates.add(reservationDate);

        Reservation reservation = Reservation.builder()
                .id(1)
                .clientFullName("Edgar Omar Lopez")
                .roomNumber(303)
                .reservationDates(reservationDates)
                .build();

        reservations.add(reservation);
        return new PageImpl<>(reservations, new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 1;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        }, reservations.size());
    }
}
