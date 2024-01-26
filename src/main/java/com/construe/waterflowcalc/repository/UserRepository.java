package com.construe.waterflowcalc.repository;

import com.construe.waterflowcalc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByLastname(String lastname);
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
}
