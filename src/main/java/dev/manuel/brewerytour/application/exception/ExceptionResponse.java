package dev.manuel.brewerytour.application.exception;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ExceptionResponse {

  private final String message;

}
