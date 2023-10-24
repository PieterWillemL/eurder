package com.switchfully.eurder.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler({EmailAlreadyInDatabaseException.class, ItemAlreadyInDatabaseException.class})
    public ResponseEntity<String> BadRequestExceptionHandler(Exception exception){
        logger.warn(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({InvalidAdminEmailException.class, InvalidCustomerEmailException.class, IncorrectPasswordException.class})
    public ResponseEntity<String> ForbiddenExceptionHandler(Exception exception){
        logger.warn(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}
