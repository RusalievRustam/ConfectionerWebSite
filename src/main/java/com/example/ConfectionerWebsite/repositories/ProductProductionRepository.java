package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.ProductProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductProductionRepository extends JpaRepository<ProductProduction, Long> {
}
