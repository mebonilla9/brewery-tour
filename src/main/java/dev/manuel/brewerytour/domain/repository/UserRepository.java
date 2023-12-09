package dev.manuel.brewerytour.domain.repository;

import dev.manuel.brewerytour.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Page<User> findAll(Pageable pageable);

  Optional<User> findUserByEmail(String email);

}
