package com.CQRSArchitecture.ProductService.core.api.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ProductDB {

    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
