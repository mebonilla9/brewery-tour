package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BeerService;
import dev.manuel.brewerytour.domain.dto.BeerDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
public record BeerController(
  BeerService beerService
) {

  @PostMapping
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> registerBeer(@RequestBody BeerDto beerDto) {
    beerService.registerBeer(beerDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findAllBeer(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BeerDto> beers = beerService.findAllBeer(offset, limit);
    return new ResponseEntity<>(beers, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findBeerById(@PathVariable("id") Integer id) throws BreweryTourException {
    BeerDto beer = beerService.findBeerById(id);
    return new ResponseEntity<>(beer, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> editBeer(@PathVariable("id") Integer id, @RequestBody BeerDto beerDto) throws BreweryTourException {
    beerService.editBeer(id, beerDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> removeBeer(@PathVariable("id") Integer id) throws BreweryTourException {
    beerService.removeBeer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
