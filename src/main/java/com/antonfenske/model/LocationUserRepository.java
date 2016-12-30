package com.antonfenske.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationUserRepository extends JpaRepository<LocationUser, Long> {
  Optional<LocationUser> findById(long id);
}
