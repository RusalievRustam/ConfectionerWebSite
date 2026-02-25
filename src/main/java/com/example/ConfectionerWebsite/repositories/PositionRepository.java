package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
