package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.mapper.BeerMapper;
import dev.manuel.brewerytour.domain.dto.BeerDto;
import dev.manuel.brewerytour.domain.entity.Beer;
import dev.manuel.brewerytour.domain.repository.BeerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BeerServiceTest {

  @Autowired
  private BeerService beerService;

  @MockBean
  private BeerMapper mapper;

  @MockBean
  private BeerRepository beerRepository;


  @Test
  void testRegisterBeer() {
    // Given
    BeerDto beerDto = new BeerDto(null, "Poker", 3500.0D, 20, null);
    Beer beer = Beer.builder()
      .name("Poker")
      .price(3500.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();

    // When
    when(mapper.toEntity(beerDto)).thenReturn(beer);
    beerService.registerBeer(beerDto);

    // Then
    verify(beerRepository).save(beer);
  }

  @Test
  @SneakyThrows
  void testFindAllBeer() {
    // Given
    final Integer offset = 0;
    final Integer limit = 100;
    Beer poker = Beer.builder()
      .id(345)
      .name("Poker")
      .price(3500.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();
    Beer costena = Beer.builder()
      .id(542)
      .name("Costeña")
      .price(3100.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();
    List<Beer> beers = Arrays.asList(poker, costena);

    List<BeerDto> expectedDtoList = Arrays.asList(
      mapper.toDto(poker), mapper.toDto(costena)
    );

    // When
    when(beerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(beers));
    when(mapper.toDtoList(beers)).thenReturn(expectedDtoList);

    List<BeerDto> result = beerService.findAllBeer(offset, limit);

    // Then
    assertEquals(expectedDtoList, result);
  }

  @Test
  void testFindAllBeerNotReturnData() {
    // Given
    final Integer offset = 0;
    final Integer limit = 100;

    // When
    when(beerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

    // Then
    assertThrows(BreweryTourException.class, () -> beerService.findAllBeer(offset, limit));
  }

  @Test
  @SneakyThrows
  void testFindBeerById() {
    // Given
    final Integer id = 345;

    BeerDto expectedDto = new BeerDto(345, "Poker", 3500.0D, 30, null);

    Beer poker = Beer.builder()
      .id(345)
      .name("Poker")
      .price(3500.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();

    // When
    when(mapper.toDto(poker)).thenReturn(expectedDto);
    when(beerRepository.findById(id)).thenReturn(Optional.of(poker));
    BeerDto beerResult = beerService.findBeerById(id);

    // Then
    assertEquals(expectedDto, beerResult);
  }

  @Test
  void testFindBeerByIdNotReturnData() {
    // Given
    final Integer id = 345;

    // When
    when(beerRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(BreweryTourException.class, () -> beerService.findBeerById(id));
  }

  @Test
  @SneakyThrows
  void testEditBeer() {
    // Given
    final Integer id = 345;

    BeerDto beerDto = new BeerDto(345, "Poker", 3700.0D, 20, null);

    Beer beerFound = Beer.builder()
      .id(345)
      .name("Poker")
      .price(3500.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();

    Beer beer = Beer.builder()
      .id(345)
      .name("Poker")
      .price(3700.0D)
      .availableQuantity(20)
      .brewery(null)
      .build();

    // When
    when(mapper.toEntity(beerDto)).thenReturn(beer);
    when(beerRepository.findById(id)).thenReturn(Optional.of(beerFound));
    beerService.editBeer(id, beerDto);

    // Then
    verify(beerRepository).save(beer);
  }

  @Test
  void testEditBeerNotReturnData() {
    // Given
    final Integer id = 345;
    BeerDto beerDto = new BeerDto(345, "Poker", 3700.0D, 20, null);

    // When
    when(beerRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(BreweryTourException.class, () -> beerService.editBeer(id, beerDto));
  }

  @Test
  @SneakyThrows
  void removeBeer() {
    // Given
    final Integer id = 345;

    Beer beerFound = Beer.builder()
      .id(345)
      .name("Poker")
      .price(3500.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();

    // When
    when(beerRepository.findById(id)).thenReturn(Optional.of(beerFound));
    beerService.removeBeer(id);

    // Then
    verify(beerRepository).delete(beerFound);
  }

  @Test
  void testRemoveBeerNotReturnData() {
    // Given
    final Integer id = 345;

    // When
    when(beerRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(BreweryTourException.class, () -> beerService.removeBeer(id));
  }
}