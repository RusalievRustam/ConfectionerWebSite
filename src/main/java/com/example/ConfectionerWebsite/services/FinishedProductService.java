package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.FinishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinishedProductService {

    private FinishedProductRepository finishedProductRepository;

    @Autowired
    public FinishedProductService(FinishedProductRepository finishedProductRepository) {
        this.finishedProductRepository = finishedProductRepository;
    }

    public FinishedProduct createFinishedProduct(FinishedProduct finishedProduct) {
        return finishedProductRepository.save(finishedProduct);
    }

    public List<FinishedProduct> getAllProducts() {
        return finishedProductRepository.findAll();
    }

    public FinishedProduct getFinishedProductById(Long id) {
        return finishedProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Finished product not found by id" + id));
    }

    public FinishedProduct updateFinishedProduct(FinishedProduct updatedFinishedProduct) {
        FinishedProduct existingProduct = getFinishedProductById(updatedFinishedProduct.getId());
        existingProduct.setName(updatedFinishedProduct.getName());
        existingProduct.setQuantity(updatedFinishedProduct.getQuantity());
        existingProduct.setTotalCost(updatedFinishedProduct.getTotalCost());
        existingProduct.setMeasurementUnit(updatedFinishedProduct.getMeasurementUnit());
        return finishedProductRepository.save(existingProduct);
    }

    public void deleteFinishedProduct(Long id) {
        finishedProductRepository.deleteById(id);
    }
}
