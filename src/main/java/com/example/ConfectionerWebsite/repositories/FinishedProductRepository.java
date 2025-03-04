package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedProductRepository extends JpaRepository<FinishedProduct, Long> {
}
