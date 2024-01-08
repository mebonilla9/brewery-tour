package dev.manuel.brewerytour.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillDetailDto(
  Integer id,
  Integer quantity,
  BillDto bill,
  BeerDto beer) {
}
