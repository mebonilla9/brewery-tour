package dev.manuel.brewerytour.domain.repository;

import dev.manuel.brewerytour.domain.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

  Page<Booking> findAll(Pageable pageable);

}
