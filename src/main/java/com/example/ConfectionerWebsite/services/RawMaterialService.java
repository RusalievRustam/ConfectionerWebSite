package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.IngredientRepository;
import com.example.ConfectionerWebsite.repositories.RawMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RawMaterialService {
    private final RawMaterialRepository rawMaterialRepository;
    private final IngredientRepository ingredientRepository;

    public void createRawMaterial(RawMaterial rawMaterial){
        RawMaterial rawMaterialFromDb = rawMaterialRepository.findByName(rawMaterial.getName());
        if(rawMaterialFromDb != null){
            rawMaterialFromDb.setQuantity(rawMaterial.getQuantity() + rawMaterialFromDb.getQuantity());
            rawMaterialFromDb.setTotalCost(rawMaterial.getTotalCost());
            rawMaterialFromDb.setMeasurementUnit(rawMaterial.getMeasurementUnit());
            rawMaterialRepository.save(rawMaterialFromDb);
        } else {
            rawMaterialRepository.save(rawMaterial);
        }
    }

    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    public RawMaterial getRawMaterialById(Long id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found with id " + id));
    }

    public void deleteRawMaterial(Long id) {
        final var ingredients = ingredientRepository.findAllByRawMaterialId(id);
        ingredients.forEach(ingredientRepository::delete);
        RawMaterial existingRawMaterial = getRawMaterialById(id);
        rawMaterialRepository.delete(existingRawMaterial);
    }

    public void updateRawMaterial(RawMaterial updatedMaterial){
        RawMaterial existingRawMaterial = getRawMaterialById(updatedMaterial.getId());
        existingRawMaterial.setName(updatedMaterial.getName());
        existingRawMaterial.setQuantity(updatedMaterial.getQuantity());
        existingRawMaterial.setTotalCost(updatedMaterial.getTotalCost());
        existingRawMaterial.setMeasurementUnit(updatedMaterial.getMeasurementUnit());
        rawMaterialRepository.save(existingRawMaterial);
    }

    public RawMaterial getMaterialByName(String name) {
        return rawMaterialRepository.findByName(name);
    }
}
