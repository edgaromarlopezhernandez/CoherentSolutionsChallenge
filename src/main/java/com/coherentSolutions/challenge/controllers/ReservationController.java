package com.coherentSolutions.challenge.controllers;

import com.coherentSolutions.challenge.dtos.PaginatedReservations;
import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;
import com.coherentSolutions.challenge.services.ReservationService;
import com.coherentSolutions.challenge.util.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "RESERVATIONS API", description = " Management APIs for everything related to hotel reservations and their reservation dates")
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService service;

    @Operation(
            summary = "Retrieve all hotel reservations",
            description = "Retrieve all reservations including their associated reservation dates.")
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations(
            @Parameter(description = "Page number, starting from 0", required = true) @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @Parameter(description = "Number of items per page", required = true) @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        Optional<PaginatedReservations> reservations = service.findAllReservations(pageNumber,pageSize);
        if(!reservations.isPresent())
            return new CustomResponse(true, "There's no reservations", null).createResponse(HttpStatus.NOT_FOUND);
        else
            return new CustomResponse(true, "Success", reservations).createResponse();
    }
    @Operation(
            summary = "Create a new hotel reservation",
            description = "Create a new reservation including its dates.")
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest payload){
        ReservationResponse reservationResponse = service.createReservation(payload);
        return new CustomResponse(true,"Success", "Reservation created successfully with ID: " + reservationResponse.getId()).createResponse(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a hotel reservation",
            description = "Update a hotel reservation including its dates.")
    @PutMapping
    public ResponseEntity<ReservationResponse> updateReservation(@RequestBody ReservationRequest reservationRequest){
        Optional<ReservationResponse> reservationUpdated = service.updateReservation(reservationRequest);
        if(!reservationUpdated.isPresent()) {
            return new CustomResponse(true, "Reservation does not exist", null).createResponse(HttpStatus.NOT_FOUND);
        } else {
            return new CustomResponse(true, "Reservation updated successfully", reservationUpdated.get()).createResponse();
        }
    }
}
