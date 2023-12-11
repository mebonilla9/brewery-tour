package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.UserService;
import dev.manuel.brewerytour.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public record UserController(
  UserService userService
) {

  @PostMapping
  public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
    userService.registerUser(userDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  public ResponseEntity<?> findAllUser(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<UserDto> users = userService.findAllUser(offset, limit);
    return new ResponseEntity<>(users, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findUserById(@PathVariable("id") Integer id) throws BreweryTourException {
    UserDto user = userService.findUserById(id);
    return new ResponseEntity<>(user, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) throws BreweryTourException {
    userService.editUser(id, userDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> removeUser(@PathVariable("id") Integer id) throws BreweryTourException {
    userService.removeUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
