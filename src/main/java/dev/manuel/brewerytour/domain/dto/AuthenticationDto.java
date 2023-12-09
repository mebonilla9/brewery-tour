package dev.manuel.brewerytour.domain.dto;

public record AuthenticationDto(
  String email,
  String password
) {
}
