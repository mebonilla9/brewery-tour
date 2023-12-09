package dev.manuel.brewerytour.domain.repository;

import dev.manuel.brewerytour.domain.entity.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Integer> {

  Page<Beer> findAll(Pageable pageable);

}
