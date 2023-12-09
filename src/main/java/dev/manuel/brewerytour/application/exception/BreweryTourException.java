package dev.manuel.brewerytour.application.exception;

import dev.manuel.brewerytour.application.lasting.EMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BreweryTourException extends Exception {

  private final HttpStatus status;
  private final String message;

  public BreweryTourException(EMessage eMessage) {
    this.status = eMessage.getStatus();
    this.message = eMessage.getMessage();
  }

}
