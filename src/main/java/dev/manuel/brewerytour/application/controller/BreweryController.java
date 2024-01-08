package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BreweryService;
import dev.manuel.brewerytour.domain.dto.BreweryDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/brewery")
public record BreweryController(
  BreweryService breweryService
) {

  @PostMapping
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> registerBrewery(@RequestBody BreweryDto breweryDto) {
    breweryService.registerBrewery(breweryDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findAllBrewery(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BreweryDto> breweries = breweryService.findAllBrewery(offset, limit);
    return new ResponseEntity<>(breweries, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findBreweryById(@PathVariable("id") Integer id) throws BreweryTourException {
    BreweryDto brewery = breweryService.findBreweryById(id);
    return new ResponseEntity<>(brewery, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> editBrewery(@PathVariable("id") Integer id, @RequestBody BreweryDto breweryDto) throws BreweryTourException {
    breweryService.editBrewery(id, breweryDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> removeBrewery(@PathVariable("id") Integer id) throws BreweryTourException {
    breweryService.removeBrewery(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
