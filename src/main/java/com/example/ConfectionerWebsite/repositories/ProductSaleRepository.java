package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.entities.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query("select coalesce(sum(ps.totalCost), 0) from ProductSale ps " +
            "where ps.date between :from and :to")
    Double sumSales(@Param("from") LocalDate from, @Param("to") LocalDate to);
}