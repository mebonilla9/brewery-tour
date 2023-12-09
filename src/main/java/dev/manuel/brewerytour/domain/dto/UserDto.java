package dev.manuel.brewerytour.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.brewerytour.application.lasting.ERole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
  Integer id,
  String name,
  String email,
  String password,
  ERole role,
  Boolean enable) {
}
