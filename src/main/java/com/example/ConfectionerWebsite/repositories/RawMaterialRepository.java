package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.RawMaterial;
import jakarta.mail.Quota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    RawMaterial findByName(String name);
}
