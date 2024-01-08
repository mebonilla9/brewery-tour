package dev.manuel.brewerytour.application.controller;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.service.BillDetailService;
import dev.manuel.brewerytour.domain.dto.BillDetailDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/bill-detail")
public record BillDetailController(
  BillDetailService billDetailService
) {

  @PostMapping
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> registerBillDetail(@RequestBody BillDetailDto billDetailDto) {
    billDetailService.registerBillDetail(billDetailDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{offset}/{limit}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findAllBillDetail(
    @PathVariable("offset") Integer offset,
    @PathVariable("limit") Integer limit) throws BreweryTourException {
    List<BillDetailDto> billDetails = billDetailService.findAllBillDetail(offset, limit);
    return new ResponseEntity<>(billDetails, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> findBillDetailById(@PathVariable("id") Integer id) throws BreweryTourException {
    BillDetailDto billDetail = billDetailService.findBillDetailById(id);
    return new ResponseEntity<>(billDetail, HttpStatus.FOUND);
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> editBillDetail(@PathVariable("id") Integer id, @RequestBody BillDetailDto billDetailDto) throws BreweryTourException {
    billDetailService.editBillDetail(id, billDetailDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<?> removeBillDetail(@PathVariable("id") Integer id) throws BreweryTourException {
    billDetailService.removeBillDetail(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
