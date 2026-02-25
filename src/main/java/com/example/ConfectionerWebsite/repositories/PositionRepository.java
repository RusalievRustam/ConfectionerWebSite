package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByName(String name);
}
