package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BillService;
import dev.manuel.brewerytour.domain.dto.BillDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
public record BillController(
  BillService billService
) {

  @PostMapping
  public ResponseEntity<?> registerBill(@RequestBody BillDto billDto) {
    billService.registerBill(billDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  public ResponseEntity<?> findAllBill(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BillDto> bills = billService.findAllBill(offset, limit);
    return new ResponseEntity<>(bills, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findBillById(@PathVariable("id") Integer id) throws BreweryTourException {
    BillDto bill = billService.findBillById(id);
    return new ResponseEntity<>(bill, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editBill(@PathVariable("id") Integer id, @RequestBody BillDto billDto) throws BreweryTourException {
    billService.editBill(id, billDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> removeBill(@PathVariable("id") Integer id) throws BreweryTourException {
    billService.removeBill(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
