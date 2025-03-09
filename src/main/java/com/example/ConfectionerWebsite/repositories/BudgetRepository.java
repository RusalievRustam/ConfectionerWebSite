package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Budget findByTotalAmount(Double totalAmount);
}
