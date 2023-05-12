package com.coherentSolutions.challenge.util.validators;

import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.exceptions.IncorrectDataBadRequestException;
import com.coherentSolutions.challenge.util.IsNumeric;
import com.coherentSolutions.challenge.util.enums.AppMessages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ReservationValidator {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.US);
    public void validateReservationToCreateANewRecord (ReservationRequest reservationRequest){
        if(reservationRequest.getId() != null)
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_ID_NOT_ALLOWED_TO_CREATE_A_NEW_RECORD.getMessage());
        if(reservationRequest.getClientFullName() == null)
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_NULL_CLIENT_FULL_NAME.getMessage());
        if(reservationRequest.getClientFullName().isEmpty())
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_EMPTY_CLIENT_FULL_NAME.getMessage());
        if(reservationRequest.getClientFullName().length() > 50)
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50.getMessage());
        if(reservationRequest.getRoomNumber() == null)
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_NULL_ROOM_NUMBER.getMessage());
        if(reservationRequest.getRoomNumber().isEmpty())
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_EMPTY_ROOM_NUMBER.getMessage());
        if(!IsNumeric.verifyUnsigned(reservationRequest.getRoomNumber()))
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC.getMessage());
        if(reservationRequest.getReservationDates() == null)
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_NULL_DATES.getMessage());
        for(int i = 0; i < reservationRequest.getReservationDates().size(); i++){
            try{
                LocalDate.parse(reservationRequest.getReservationDates().get(i), formatter);
            } catch(Exception e) {
                throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_INCORRECT_DATES.getMessage());
            }
        }
    }

    public void validateReservationToUpdateARecord (ReservationRequest reservationRequest){
        if(reservationRequest.getId() == null)
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_NULL_ID.getMessage());
        if(reservationRequest.getId().isEmpty())
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_EMPTY_ID.getMessage());
        if(!IsNumeric.verifyUnsigned(reservationRequest.getId()))
            throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_ID_IS_NOT_A_NUMERIC_VALUE.getMessage());
        if(reservationRequest.getClientFullName() != null) {
            if(reservationRequest.getClientFullName().isEmpty())
                throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_EMPTY_CLIENT_FULL_NAME.getMessage());
            if(reservationRequest.getClientFullName().length() > 50)
                throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50.getMessage());
        }
        if(reservationRequest.getRoomNumber() != null) {
            if(reservationRequest.getRoomNumber().isEmpty())
                throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_EMPTY_ROOM_NUMBER.getMessage());
            if(!IsNumeric.verifyUnsigned(reservationRequest.getRoomNumber()))
                throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC.getMessage());
        }
        if(reservationRequest.getReservationDates() != null) {
            for(int i = 0; i < reservationRequest.getReservationDates().size(); i++){
                try{
                    LocalDate.parse(reservationRequest.getReservationDates().get(i), formatter);
                } catch(Exception e) {
                    throw new IncorrectDataBadRequestException(AppMessages.RESERVATION_INCORRECT_DATES.getMessage());
                }
            }
        }
    }
}
