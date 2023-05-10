package com.coherentSolutions.challenge.util.converters;

import com.coherentSolutions.challenge.dtos.ReservationResponse;
import com.coherentSolutions.challenge.exceptions.DataNotFoundException;
import com.coherentSolutions.challenge.models.Reservation;
import com.coherentSolutions.challenge.models.ReservationDate;
import com.coherentSolutions.challenge.repositories.ReservationDAO;
import com.coherentSolutions.challenge.repositories.ReservationDateDAO;
import com.coherentSolutions.challenge.util.enums.AppMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ReservationConverter extends AbstractConverter<Reservation, ReservationResponse> {
    @Autowired
    private ReservationDateDAO reservationDateDAO;

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    public ReservationResponse fromEntity(Reservation entity) {
        if(entity == null)
            throw new RuntimeException("Null entity in reservation converter not allowed");

        List<String> lines = getReservationDatesByReservationId(entity.getId());

        return ReservationResponse.builder()
                .id(entity.getId())
                .clientFullName(entity.getClientFullName())
                .roomNumber(entity.getRoomNumber())
                .reservationDates(lines)
                .build();
    }

    public List<String> getReservationDatesByReservationId(Integer id) {
        List<ReservationDate> list = reservationDateDAO.findAllDatesByReservationId(id);
        List<String> reservationList = new ArrayList<>();

        if(list.size() != 0){
            for(ReservationDate value: list)
                reservationList.add(value.getReservationDate().toString());
        }

        return reservationList;
    }

    private List<String> fromReservationDateEntity(List<ReservationDate> lines) {
        if(lines == null) return null;
        List<String> dates = new ArrayList<>();
        for(ReservationDate reservationDate: lines){
            dates.add(reservationDate.getReservationDate().toString());
        }
        return dates;
    }

    @Override
    public Reservation fromDTO(ReservationResponse dto) {
        if (dto == null)
            throw new RuntimeException("Null dto in reservation converter not allowed");

        List<ReservationDate> lines = setListOfReservationDates(dto.getReservationDates(), dto.getId());

        return Reservation.builder()
                .clientFullName(dto.getClientFullName())
                .roomNumber(Integer.valueOf(dto.getRoomNumber()))
                .reservationDates(lines)
                .build();
    }

    private List<ReservationDate> setListOfReservationDates(List<String> reservationDates, Integer id){
        Reservation finalReservationToUpdate = reservationDAO.findById(id)
                .orElseThrow(() -> new DataNotFoundException(AppMessages.RESERVATION_DOES_NOT_EXIST.getMessage()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.US );
        List<ReservationDate> dates = new ArrayList<>();
        for(String value: reservationDates){
            ReservationDate reservationDate = ReservationDate.builder()
                    .reservationDate(LocalDate.parse(value, formatter))
                    .build();
            dates.add(reservationDate);
        }
        dates.forEach(reservationDate -> reservationDate.setReservation(finalReservationToUpdate));
        return reservationDateDAO.saveAll(dates);
    }
}
