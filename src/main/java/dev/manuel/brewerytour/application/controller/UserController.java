package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.UserService;
import dev.manuel.brewerytour.domain.dto.UserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public record UserController(
  UserService userService
) {

  @PostMapping
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
    userService.registerUser(userDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findAllUser(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<UserDto> users = userService.findAllUser(offset, limit);
    return new ResponseEntity<>(users, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findUserById(@PathVariable("id") Integer id) throws BreweryTourException {
    UserDto user = userService.findUserById(id);
    return new ResponseEntity<>(user, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) throws BreweryTourException {
    userService.editUser(id, userDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> removeUser(@PathVariable("id") Integer id) throws BreweryTourException {
    userService.removeUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
