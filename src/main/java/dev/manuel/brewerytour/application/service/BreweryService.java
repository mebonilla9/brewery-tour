package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.BreweryMapper;
import dev.manuel.brewerytour.domain.dto.BreweryDto;
import dev.manuel.brewerytour.domain.entity.Brewery;
import dev.manuel.brewerytour.domain.repository.BreweryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BreweryService(
  BreweryRepository breweryRepository,
  BreweryMapper mapper
) {

  public void registerBrewery(BreweryDto breweryDto) {
    Brewery brewery = mapper.toEntity(breweryDto);
    breweryRepository.save(brewery);
  }

  public List<BreweryDto> findAllBrewery(Integer offset, Integer limit) throws BreweryTourException {
    Pageable pageable = PageRequest.of(offset, limit);
    Page<Brewery> breweries = breweryRepository.findAll(pageable);
    if (breweries.getContent().isEmpty()) {
      throw new BreweryTourException(EMessage.DATA_NOT_FOUND);
    }
    return mapper.toDtoList(breweries.getContent());
  }

  public BreweryDto findBreweryById(Integer id) throws BreweryTourException {
    Brewery brewery = breweryRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    return mapper.toDto(brewery);
  }

  public void editBrewery(Integer id, BreweryDto breweryDto) throws BreweryTourException {
    breweryRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    Brewery brewery = mapper.toEntity(breweryDto);
    breweryRepository.save(brewery);
  }

  public void removeBrewery(Integer id) throws BreweryTourException {
    Brewery brewery = breweryRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    breweryRepository.delete(brewery);
  }

}
