package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.BillDetailMapper;
import dev.manuel.brewerytour.domain.dto.BillDetailDto;
import dev.manuel.brewerytour.domain.entity.BillDetail;
import dev.manuel.brewerytour.domain.repository.BillDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BillDetailService(
  BillDetailRepository billDetailRepository,
  BillDetailMapper mapper
) {

  public void registerBillDetail(BillDetailDto billDetailDto) {
    BillDetail billDetail = mapper.toEntity(billDetailDto);
    billDetailRepository.save(billDetail);
  }

  public List<BillDetailDto> findAllBillDetail(Integer offset, Integer limit) throws BreweryTourException {
    Pageable pageable = PageRequest.of(offset, limit);
    Page<BillDetail> breweries = billDetailRepository.findAll(pageable);
    if (breweries.getContent().isEmpty()) {
      throw new BreweryTourException(EMessage.DATA_NOT_FOUND);
    }
    return mapper.toDtoList(breweries.getContent());
  }

  public BillDetailDto findBillDetailById(Integer id) throws BreweryTourException {
    BillDetail billDetail = billDetailRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    return mapper.toDto(billDetail);
  }

  public void editBillDetail(Integer id, BillDetailDto billDetailDto) throws BreweryTourException {
    billDetailRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    BillDetail billDetail = mapper.toEntity(billDetailDto);
    billDetailRepository.save(billDetail);
  }

  public void removeBillDetail(Integer id) throws BreweryTourException {
    BillDetail billDetail = billDetailRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    billDetailRepository.delete(billDetail);
  }

}
