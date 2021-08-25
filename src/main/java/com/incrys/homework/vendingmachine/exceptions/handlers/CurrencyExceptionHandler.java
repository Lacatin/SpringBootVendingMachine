package com.incrys.homework.vendingmachine.exceptions.handlers;

import com.incrys.homework.vendingmachine.exceptions.CustomErrorResponse;
import com.incrys.homework.vendingmachine.exceptions.InvalidBillTypeException;
import com.incrys.homework.vendingmachine.exceptions.InvalidCoinTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CurrencyExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> invalidBillHandler(InvalidBillTypeException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> invalidCoinHandler(InvalidCoinTypeException e){

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
