package com.CQRSArchitecture.ProductService.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDB, String> {

}
