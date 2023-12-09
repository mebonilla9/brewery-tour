package dev.manuel.brewerytour.application.lasting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EMessage {

  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "The data of the user was not found"),
  DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "The data was not found");

  private final HttpStatus status;
  private final String message;

}
