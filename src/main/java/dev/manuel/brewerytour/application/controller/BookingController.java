package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BookingService;
import dev.manuel.brewerytour.domain.dto.BookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public record BookingController(
  BookingService bookingService
) {

  @PostMapping
  public ResponseEntity<?> registerBooking(@RequestBody BookingDto bookingDto) {
    bookingService.registerBooking(bookingDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  public ResponseEntity<?> findAllBooking(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BookingDto> bookings = bookingService.findAllBooking(offset, limit);
    return new ResponseEntity<>(bookings, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findBookingById(@PathVariable("id") Integer id) throws BreweryTourException {
    BookingDto booking = bookingService.findBookingById(id);
    return new ResponseEntity<>(booking, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editBooking(@PathVariable("id") Integer id, @RequestBody BookingDto bookingDto) throws BreweryTourException {
    bookingService.editBooking(id, bookingDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> removeBooking(@PathVariable("id") Integer id) throws BreweryTourException {
    bookingService.removeBooking(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
}
