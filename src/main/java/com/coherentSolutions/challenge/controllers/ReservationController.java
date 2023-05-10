package com.coherentSolutions.challenge.controllers;

import com.coherentSolutions.challenge.dtos.PaginatedReservations;
import com.coherentSolutions.challenge.dtos.ReservationRequest;
import com.coherentSolutions.challenge.dtos.ReservationResponse;
import com.coherentSolutions.challenge.services.ReservationService;
import com.coherentSolutions.challenge.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService service;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        Optional<PaginatedReservations> reservations = service.findAllReservations(pageNumber,pageSize);
        if(!reservations.isPresent())
            return new CustomResponse(true, "There's no reservations", null).createResponse(HttpStatus.NOT_FOUND);
        else
            return new CustomResponse(true, "Success", reservations).createResponse();
    }
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest payload){
        System.out.println("Imprimiendo el payload**********************************");
        ReservationResponse reservationResponse = service.createReservation(payload);
        return new CustomResponse(true,"Success", "Reservation created successfully with ID: " + reservationResponse.getId()).createResponse(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ReservationResponse> updateReservation(@RequestBody ReservationRequest reservationRequest){
        Optional<ReservationResponse> reservationUpdated = service.updateReservation(reservationRequest);
        if(!reservationUpdated.isPresent()) {
            return new CustomResponse(true, "Reservation does not exist", null).createResponse(HttpStatus.NOT_FOUND);
        } else {
            return new CustomResponse(true, "Reservation updated successfully", reservationUpdated.get()).createResponse(HttpStatus.NOT_FOUND);
        }
    }
}
