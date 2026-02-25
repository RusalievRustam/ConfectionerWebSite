package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.RawMaterial;
import jakarta.mail.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    RawMaterial findByName(String name);

    @Query("select coalesce(sum(p.totalCost), 0) from RawMaterialPurchase p " +
            "where p.date between :from and :to")
    Double sumPurchases(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
