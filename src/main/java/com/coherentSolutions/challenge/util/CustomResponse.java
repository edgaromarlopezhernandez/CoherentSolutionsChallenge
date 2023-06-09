package com.coherentSolutions.challenge.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    private boolean ok;
    private String message;
    private T content;

    public ResponseEntity<CustomResponse<T>> createResponse(){
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<CustomResponse<T>> createResponse(HttpStatus status){
        return new ResponseEntity<>(this,status);
    }
}