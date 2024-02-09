package com.CQRSArchitecture.ProductService.query.api.projection;

import com.CQRSArchitecture.ProductService.core.api.repository.ProductDB;
import com.CQRSArchitecture.ProductService.core.api.repository.ProductRepository;
import com.CQRSArchitecture.ProductService.core.api.model.ProductRestModel;
import com.CQRSArchitecture.ProductService.query.api.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery getProductQuery){
        List<ProductDB> productDB = productRepository.findAll();

        List<ProductRestModel> productRestModels = productDB.stream().map(
                productDB1 -> ProductRestModel
                        .builder()
                        .quantity(productDB1.getQuantity())
                        .name(productDB1.getName())
                        .price(productDB1.getPrice())
                        .build()).collect(Collectors.toList());
        return productRestModels;
    }

}
