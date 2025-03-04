package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.repositories.FinishedProductRepository;
import com.example.ConfectionerWebsite.repositories.MeasurementUnitRepository;
import com.example.ConfectionerWebsite.repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    FinishedProductRepository finishedProductRepository;
    @Autowired
    RawMaterialRepository rawMaterialRepository;
    @Autowired
    MeasurementUnitRepository measurementUnitRepository;

    public FinishedProduct addProduct(FinishedProduct product) {
        return finishedProductRepository.save(product);
    }

    public RawMaterial addRawMaterial(RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    public List<FinishedProduct> getAllFinishedProducts() {
        return finishedProductRepository.findAll();
    }

    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    public void deleteFinishedProduct(Long id){
        finishedProductRepository.deleteById(id);
    }

    public void deleteRawMaterials(Long id){
        rawMaterialRepository.deleteById(id);
    }

}
