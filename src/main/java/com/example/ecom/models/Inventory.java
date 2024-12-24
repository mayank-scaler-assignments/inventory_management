package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Inventory extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
}
