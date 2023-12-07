package dev.manuel.brewerytour.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BeerDto(
  Integer id,
  String name,
  Double price,
  Integer availableQuantity,
  BreweryDto brewery) {
}
