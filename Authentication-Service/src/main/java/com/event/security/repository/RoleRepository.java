package com.event.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.event.security.models.ERole;
import com.event.security.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
