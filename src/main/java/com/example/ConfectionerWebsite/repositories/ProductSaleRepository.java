package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.entities.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale,Long> {

}
