package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BillDetailService;
import dev.manuel.brewerytour.domain.dto.BillDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill-detail")
public record BillDetailController(
  BillDetailService billDetailService
) {

  @PostMapping
  public ResponseEntity<?> registerBillDetail(@RequestBody BillDetailDto billDetailDto) {
    billDetailService.registerBillDetail(billDetailDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  public ResponseEntity<?> findAllBillDetail(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BillDetailDto> billDetails = billDetailService.findAllBillDetail(offset, limit);
    return new ResponseEntity<>(billDetails, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findBillDetailById(@PathVariable("id") Integer id) throws BreweryTourException {
    BillDetailDto billDetail = billDetailService.findBillDetailById(id);
    return new ResponseEntity<>(billDetail, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editBillDetail(@PathVariable("id") Integer id, @RequestBody BillDetailDto billDetailDto) throws BreweryTourException {
    billDetailService.editBillDetail(id, billDetailDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> removeBillDetail(@PathVariable("id") Integer id) throws BreweryTourException {
    billDetailService.removeBillDetail(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
