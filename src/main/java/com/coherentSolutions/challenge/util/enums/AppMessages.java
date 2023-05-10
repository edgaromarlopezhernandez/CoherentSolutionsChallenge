package com.coherentSolutions.challenge.util.enums;

public enum AppMessages {

    RESERVATION_DOES_NOT_EXIST("Reservation doesn't exist"),
    RESERVATION_ID_IS_NOT_A_NUMERIC_VALUE("Empty \"ID\" not allowed"),
    RESERVATION_EMPTY_ID("Empty \"ID\" not allowed"),
    RESERVATION_NULL_ID("Null \"ID\" not allowed"),
    RESERVATION_INCORRECT_DATES("The correct format for dates is: yyyy-MM-dd. An example for May 9th, 2023 would be: \"2023-05-09\""),
    RESERVATION_NULL_DATES("Null \"RESERVATION DATES\" not allowed"),
    RESERVATION_ROOM_NUMBER_IS_NOT_NUMERIC(" \"ROOM NUMBER\" is not a numeric field"),
    RESERVATION_EMPTY_ROOM_NUMBER("Empty \"ROOM NUMBER\" not allowed"),
    RESERVATION_NULL_ROOM_NUMBER("Null \"ROOM NUMBER\" not allowed"),
    RESERVATION_CLIENT_FULL_NAME_LENGTH_GRATER_THAN_50("Empty \"CLIENT FULL NAME\" not allowed"),
    RESERVATION_EMPTY_CLIENT_FULL_NAME("Empty \"CLIENT FULL NAME\" not allowed"),
    RESERVATION_NULL_CLIENT_FULL_NAME("Null \"CLIENT FULL NAME\" not allowed");


    private final String message;
    AppMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
