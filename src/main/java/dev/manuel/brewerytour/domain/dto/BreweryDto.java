package dev.manuel.brewerytour.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BreweryDto(
  Integer id,
  String name,
  String location,
  String image,
  String address,
  String phone) {
}
