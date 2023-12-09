package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.service.AuthenticationService;
import dev.manuel.brewerytour.domain.dto.AuthenticationDto;
import dev.manuel.brewerytour.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public record AutheticationController(
  AuthenticationService authenticationService
) {

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDto userDto) {
    String token = authenticationService.register(userDto);
    return new ResponseEntity<>(token, HttpStatus.CREATED);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationDto authenticationDto) {
    String token = authenticationService.authenticate(authenticationDto);
    return new ResponseEntity<>(token, HttpStatus.OK);
  }

}
