package com.coherentSolutions.challenge.util.validators;

import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.exceptions.IncorrectDataBadRequestException;
import com.coherentSolutions.challenge.util.ReservationStubs;
import com.coherentSolutions.challenge.util.enums.AppMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
public class ReservationValidatorTest {

    @InjectMocks
    private ReservationValidator validator;

    @DisplayName("Should throw RESERVATION_ID_NOT_ALLOWED_TO_CREATE_A_NEW_RECORD exception if ID is not null. ID not needed to create a new reservation.")
    @Test
    void shouldThrowReservationIdNotAllowedToCreateANewRecordExceptionIfIdIsNotNull() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            validator.validateReservationToCreateANewRecord(ReservationStubs.getReservationRequestStub());
        });
        Assertions.assertEquals(AppMessages.RESERVATION_ID_NOT_ALLOWED_TO_CREATE_A_NEW_RECORD.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_NULL_CLIENT_FULL_NAME exception if ClientFullName is null.")
    @Test
    void shouldThrowReservationNullClientFullNameExceptionIfClientFullNameIsNull() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setClientFullName(null);
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_NULL_CLIENT_FULL_NAME.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_EMPTY_CLIENT_FULL_NAME exception if ClientFullName is empty.")
    @Test
    void shouldThrowReservationEmptyClientFullNameExceptionIfClientFullNameIsEmpty() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setClientFullName("");
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_EMPTY_CLIENT_FULL_NAME.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50 exception if ClientFullName is grater than 50.")
    @Test
    void shouldThrowReservationClientFullNameLengthGraterThan50ExceptionIfClientFullNameIsGraterThan50Chars() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setClientFullName("Name too long for test Name too long for test Name too long for test");
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_NULL_ROOM_NUMBER exception if RoomNumber is null.")
    @Test
    void shouldThrowReservationNullRoomNumberIfRoomNumberIsNull() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setRoomNumber(null);
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_NULL_ROOM_NUMBER.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_EMPTY_ROOM_NUMBER exception if RoomNumber is empty.")
    @Test
    void shouldThrowReservationEmptyRoomNumberIfRoomNumberIsEmpty() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setRoomNumber("");
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_EMPTY_ROOM_NUMBER.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC exception if RoomNumber is not numeric.")
    @Test
    void shouldThrowReservationRoomNumberIsNotNumericIfRoomNumberIsNotNumeric() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setRoomNumber("Not numeric");
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_NULL_DATES exception if ReservationDates is null. We need dates for the reservation; otherwise, a reservation doesn't make sense")
    @Test
    void shouldThrowReservationNullDatesIfReservationDatesIsNull() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setReservationDates(null);
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_NULL_DATES.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_INCORRECT_DATES exception if ReservationDates doesn't have a correct format(yyyy-MM-dd).")
    @Test
    void shouldThrowReservationIncorrectDatesIfReservationDatesDoesNotHaveACorrectFormat() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            reservationRequest.setReservationDates(Arrays.asList("2023/05/10"));
            validator.validateReservationToCreateANewRecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_INCORRECT_DATES.getMessage(), incorrectDataBadRequestException.getMessage());
    }


    @DisplayName("Should throw RESERVATION_NULL_ID exception if ID is null. ID required to update a reservation.")
    @Test
    void shouldThrowReservationNullIdExceptionIfIdIsNull() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId(null);
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_NULL_ID.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_EMPTY_ID exception if ID is empty.")
    @Test
    void shouldThrowReservationEmptyIdExceptionIfIdIsEmpty() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId("");
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_EMPTY_ID.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_ID_IS_NOT_A_NUMERIC_VALUE exception if ID is not numeric.")
    @Test
    void shouldThrowReservationIdIsNotNumericExceptionIfIdIsNotNumeric() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setId("Not a numeric value");
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_ID_IS_NOT_A_NUMERIC_VALUE.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_EMPTY_CLIENT_FULL_NAME exception if ClientFullName pretend to be updated and is empty.")
    @Test
    void shouldThrowReservationEmptyClientFullNameExceptionIfClientFullNameIsEmptyToUpdate() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setClientFullName("");
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_EMPTY_CLIENT_FULL_NAME.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50 exception if ClientFullName pretend to be updated and is grater than 50 chars.")
    @Test
    void shouldThrowReservationClientFullNameLengthGraterThan50ExceptionIfClientFullNameIsLengthGraterThan50ToUpdate() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setClientFullName("Name too long for test Name too long for test Name too long for test");
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_EMPTY_ROOM_NUMBER exception if RoomNumber pretend to be updated and is empty.")
    @Test
    void shouldThrowReservationEmptyRoomNumberExceptionIfRoomNumberIsEmptyToUpdate() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setRoomNumber("");
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_EMPTY_ROOM_NUMBER.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC exception if RoomNumber pretend to be updated and is not a numeric value.")
    @Test
    void shouldThrowReservationRoomNumberIsNotNumericExceptionIfRoomNumberIsNotNumericToUpdate() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setRoomNumber("Not a numeric value");
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC.getMessage(), incorrectDataBadRequestException.getMessage());
    }

    @DisplayName("Should throw RESERVATION_INCORRECT_DATES exception if ReservationDates pretend to be updated and doesn't have a correct format.")
    @Test
    void shouldThrowReservationIncorrectDatesExceptionIfReservationDatesDoesNotHaveACorrectFormatToUpdate() {

        IncorrectDataBadRequestException incorrectDataBadRequestException = Assertions.assertThrows(IncorrectDataBadRequestException.class, () -> {
            ReservationRequest reservationRequest = ReservationStubs.getReservationRequestStub();
            reservationRequest.setReservationDates(Arrays.asList("2023/05/10"));
            validator.validateReservationToUpdateARecord(reservationRequest);
        });
        Assertions.assertEquals(AppMessages.RESERVATION_INCORRECT_DATES.getMessage(), incorrectDataBadRequestException.getMessage());
    }
}
