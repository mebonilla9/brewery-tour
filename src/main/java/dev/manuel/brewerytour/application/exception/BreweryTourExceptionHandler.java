package dev.manuel.brewerytour.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BreweryTourExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BreweryTourException.class)
  public ResponseEntity<?> handleBreweryTourException(BreweryTourException exception, WebRequest request) {
    ExceptionResponse response = ExceptionResponse.builder()
      .message(exception.getMessage()).build();
    return new ResponseEntity<>(response, exception.getStatus());
  }

}
