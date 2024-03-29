package com.construe.waterflowcalc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PipeRepository extends JpaRepository<Pipe, Long> {
    Optional<Pipe> findByLocationAndProjectNameAndChainage (String location, String projectName, String chainage);
}
