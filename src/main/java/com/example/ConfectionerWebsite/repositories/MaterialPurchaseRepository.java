package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.RawMaterialPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialPurchaseRepository extends JpaRepository<RawMaterialPurchase, Long> {
}
