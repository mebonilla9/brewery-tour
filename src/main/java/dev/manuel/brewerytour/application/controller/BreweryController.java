package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BreweryService;
import dev.manuel.brewerytour.domain.dto.BreweryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brewery")
public record BreweryController(
  BreweryService breweryService
) {

  @PostMapping
  public ResponseEntity<?> registerBrewery(@RequestBody BreweryDto breweryDto) {
    breweryService.registerBrewery(breweryDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  public ResponseEntity<?> findAllBrewery(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BreweryDto> breweries = breweryService.findAllBrewery(offset, limit);
    return new ResponseEntity<>(breweries, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findBreweryById(@PathVariable("id") Integer id) throws BreweryTourException {
    BreweryDto brewery = breweryService.findBreweryById(id);
    return new ResponseEntity<>(brewery, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editBrewery(@PathVariable("id") Integer id, @RequestBody BreweryDto breweryDto) throws BreweryTourException {
    breweryService.editBrewery(id, breweryDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> removeBrewery(@PathVariable("id") Integer id) throws BreweryTourException {
    breweryService.removeBrewery(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
