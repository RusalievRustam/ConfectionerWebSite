package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.MeasurementUnit;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementUnitService {

    private MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    public MeasurementUnitService(MeasurementUnitRepository measurementUnitRepository){
        this.measurementUnitRepository = measurementUnitRepository;
    }

    public MeasurementUnit createMeasurementUnit(MeasurementUnit measurementUnit){
        return measurementUnitRepository.save(measurementUnit);
    }

    public List<MeasurementUnit> getAllMeasurementUnits(){
        return measurementUnitRepository.findAll();
    }

    public MeasurementUnit getMeasurementUnitById(Long id){
        return measurementUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Measurement unit not found by id " + id));
    }

    public MeasurementUnit updateMeasurementUnit(Long id, MeasurementUnit updatedMeasurementUnit){
        MeasurementUnit existingUnit = getMeasurementUnitById(id);
        existingUnit.setName(updatedMeasurementUnit.getName());
        return measurementUnitRepository.save(existingUnit);
    }

    public void deleteMeasurementUnit(Long id){
        measurementUnitRepository.deleteById(id);
    }
}
