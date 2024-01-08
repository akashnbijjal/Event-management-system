package com.event.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.event.security.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
