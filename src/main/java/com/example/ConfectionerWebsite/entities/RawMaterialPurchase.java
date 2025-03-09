package com.example.ConfectionerWebsite.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "raw_material_purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterialPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Double getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
