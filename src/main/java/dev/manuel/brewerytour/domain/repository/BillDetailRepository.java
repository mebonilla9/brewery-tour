package dev.manuel.brewerytour.domain.repository;

import dev.manuel.brewerytour.domain.entity.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

  Page<BillDetail> findAll(Pageable pageable);

}
