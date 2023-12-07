package dev.manuel.brewerytour.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillDto(
  Integer id,
  LocalDateTime date,
  Double totalPrice,
  BreweryDto brewery,
  UserDto user) {
}
