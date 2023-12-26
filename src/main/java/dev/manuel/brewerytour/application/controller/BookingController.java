package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BookingService;
import dev.manuel.brewerytour.domain.dto.BookingDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/booking")
public record BookingController(
  BookingService bookingService
) {

  @PostMapping
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> registerBooking(@RequestBody BookingDto bookingDto) {
    bookingService.registerBooking(bookingDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findAllBooking(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BookingDto> bookings = bookingService.findAllBooking(offset, limit);
    return new ResponseEntity<>(bookings, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findBookingById(@PathVariable("id") Integer id) throws BreweryTourException {
    BookingDto booking = bookingService.findBookingById(id);
    return new ResponseEntity<>(booking, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> editBooking(@PathVariable("id") Integer id, @RequestBody BookingDto bookingDto) throws BreweryTourException {
    bookingService.editBooking(id, bookingDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> removeBooking(@PathVariable("id") Integer id) throws BreweryTourException {
    bookingService.removeBooking(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
}
