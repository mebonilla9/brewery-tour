package dev.manuel.brewerytour.domain.repository;

import dev.manuel.brewerytour.domain.entity.Brewery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Integer> {

  Page<Brewery> findAll(Pageable pageable);

}
