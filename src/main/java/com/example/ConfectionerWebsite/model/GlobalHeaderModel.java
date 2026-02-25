package com.example.ConfectionerWebsite.model;

import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.repositories.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHeaderModel {

    private final PositionRepository positionRepository;

    @ModelAttribute("allPositions")
    public List<Position> allPositions() {
        return positionRepository.findAll();
    }
}