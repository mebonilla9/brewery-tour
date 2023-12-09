package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.BeerMapper;
import dev.manuel.brewerytour.domain.dto.BeerDto;
import dev.manuel.brewerytour.domain.entity.Beer;
import dev.manuel.brewerytour.domain.repository.BeerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BeerService(BeerRepository beerRepository, BeerMapper mapper) {

  public void registerBeer(BeerDto beerDto) {
    Beer beer = mapper.toEntity(beerDto);
    beerRepository.save(beer);
  }

  public List<BeerDto> findAllBeer(Integer offset, Integer limit) throws BreweryTourException {
    Pageable pageable = PageRequest.of(offset, limit);
    Page<Beer> breweries = beerRepository.findAll(pageable);
    if (breweries.getContent().isEmpty()) {
      throw new BreweryTourException(EMessage.DATA_NOT_FOUND);
    }
    return mapper.toDtoList(breweries.getContent());
  }

  public BeerDto findBeerById(Integer id) throws BreweryTourException {
    Beer beer = beerRepository.findById(id).orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    return mapper.toDto(beer);
  }

  public void editBeer(Integer id, BeerDto beerDto) throws BreweryTourException {
    beerRepository.findById(id).orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    Beer beer = mapper.toEntity(beerDto);
    beerRepository.save(beer);
  }

  public void removeBeer(Integer id) throws BreweryTourException {
    Beer beer = beerRepository.findById(id).orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    beerRepository.delete(beer);
  }

}
