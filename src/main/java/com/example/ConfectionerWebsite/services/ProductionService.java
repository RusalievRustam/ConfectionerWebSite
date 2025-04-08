package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.ProductProduction;
import com.example.ConfectionerWebsite.repositories.ProductionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductionService {

    private ProductionRepository productionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void callProduceProductProcedure(Long finishedProductId, Double quantity, Long employeeId) {
        entityManager.createNativeQuery("CALL produce_product(:fp_id, :qty, :emp_id)")
                .setParameter("fp_id", finishedProductId)
                .setParameter("qty", quantity.intValue()) // Преобразуем к int, если нужно
                .setParameter("emp_id", employeeId)
                .executeUpdate();
    }

    public List<ProductProduction> getAllProductions(){
        return productionRepository.findAll();
    }

}
