package dev.manuel.brewerytour.domain.repository;

import dev.manuel.brewerytour.domain.entity.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BeerRepositoryTest {

  @Autowired
  private BeerRepository beerRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testSaveBeer(){
    // Given
    Beer beer = Beer.builder()
      .name("Poker")
      .price(3000.0D)
      .availableQuantity(100)
      .build();

    // Then
    Beer savedBeer = beerRepository.save(beer);

    // When
    assertNotNull(savedBeer.getId());
  }

  @Test
  public void testFindAllBeer(){
    // Given
    final int offset = 0;
    final int limit = 2;
    Beer poker = Beer.builder()
      .name("Poker")
      .price(3500.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();
    Beer costena = Beer.builder()
      .name("Costeña")
      .price(3100.0D)
      .availableQuantity(30)
      .brewery(null)
      .build();

    // When
    entityManager.persist(poker);
    entityManager.persist(costena);

    Pageable pageable = PageRequest.of(offset,limit);
    Page<Beer> page = beerRepository.findAll(pageable);

    // Then
    assertThat(page.getContent().size()).isEqualTo(2);
    assertThat(page.getNumber()).isEqualTo(0);
    assertThat(page.getTotalElements()).isEqualTo(2);
  }

}
