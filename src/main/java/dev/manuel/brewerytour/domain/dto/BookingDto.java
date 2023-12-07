package dev.manuel.brewerytour.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.brewerytour.application.lasting.EState;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookingDto(
  Integer id,
  LocalDateTime date,
  Integer quantity,
  EState state,
  BreweryDto brewery,
  UserDto user) {
}
