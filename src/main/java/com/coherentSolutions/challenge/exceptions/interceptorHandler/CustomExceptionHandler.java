package com.coherentSolutions.challenge.exceptions.interceptorHandler;

import com.coherentSolutions.challenge.exceptions.DataNotFoundException;
import com.coherentSolutions.challenge.exceptions.GeneralException;
import com.coherentSolutions.challenge.exceptions.IncorrectDataBadRequestException;
import com.coherentSolutions.challenge.util.CustomResponse;
import com.coherentSolutions.challenge.util.enums.AppMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request){
        log.error(AppMessages.ERROR_EXCEPTION.getMessage());
        log.error(exception.getMessage());
        log.error(Arrays.toString(exception.getStackTrace()));
        log.error(request.getDescription(true));
        log.error(request.getContextPath());
        log.error(AppMessages.ERROR_EXCEPTION.getMessage());
        return new CustomResponse(false, "[Exception]Internal Server Error", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectDataBadRequestException.class)
    public ResponseEntity<Object> handleBadRequestExceptions(IncorrectDataBadRequestException exception, WebRequest request){
        log.info(request.getDescription(true), request.getContextPath(), request.getHeaderNames());
        return new CustomResponse(false, exception.getMessage(), null).createResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundExceptions(DataNotFoundException exception, WebRequest request){
        log.info(request.getDescription(true), request.getContextPath());// check other features of webRequest
        return new CustomResponse(false, exception.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralExceptions(GeneralException exception, WebRequest request){
        log.error(AppMessages.ERROR_EXCEPTION.getMessage());
        log.error(exception.getMessage());
        log.error(String.valueOf(exception.getStackTrace()));
        log.error(request.getDescription(true));
        log.error(request.getContextPath());
        log.error(AppMessages.ERROR_EXCEPTION.getMessage());
        return new CustomResponse(false, "[General exception]Internal Server Error", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}