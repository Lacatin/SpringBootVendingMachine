package com.incrys.homework.vendingmachine.exceptions.handlers;

import com.incrys.homework.vendingmachine.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ItemsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> notAnExistingItemHandler(NotAnExistingItemException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> notAValidItemBodyHandler(NotAValidItemBodyException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> notFullPaidHandler(NotFullPaidException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.PAYMENT_REQUIRED.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> notSufficientChangeHandler(NotSufficientChangeException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> soldOutHandler(SoldOutException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> alreadyInDBHandler(AlreadyInDatabaseException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.ALREADY_REPORTED.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
    }

}
