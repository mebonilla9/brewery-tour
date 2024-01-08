package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.BillMapper;
import dev.manuel.brewerytour.domain.dto.BillDto;
import dev.manuel.brewerytour.domain.entity.Bill;
import dev.manuel.brewerytour.domain.repository.BillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BillService(
  BillRepository billRepository,
  BillMapper mapper
) {

  public void registerBill(BillDto billDto) {
    Bill bill = mapper.toEntity(billDto);
    billRepository.save(bill);
  }

  public List<BillDto> findAllBill(Integer offset, Integer limit) throws BreweryTourException {
    Pageable pageable = PageRequest.of(offset, limit);
    Page<Bill> breweries = billRepository.findAll(pageable);
    if (breweries.getContent().isEmpty()) {
      throw new BreweryTourException(EMessage.DATA_NOT_FOUND);
    }
    return mapper.toDtoList(breweries.getContent());
  }

  public BillDto findBillById(Integer id) throws BreweryTourException {
    Bill bill = billRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    return mapper.toDto(bill);
  }

  public void editBill(Integer id, BillDto billDto) throws BreweryTourException {
    billRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    Bill bill = mapper.toEntity(billDto);
    billRepository.save(bill);
  }

  public void removeBill(Integer id) throws BreweryTourException {
    Bill bill = billRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    billRepository.delete(bill);
  }

}
