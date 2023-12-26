package dev.manuel.brewerytour.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BreweryTourExceptionHandler {

  @ExceptionHandler(BreweryTourException.class)
  public ResponseEntity<?> handleBreweryTourException(BreweryTourException exception, WebRequest request) {
    ExceptionResponse response = new ExceptionResponse(exception.getMessage());
    return new ResponseEntity<>(response, exception.getStatus());
  }

}
