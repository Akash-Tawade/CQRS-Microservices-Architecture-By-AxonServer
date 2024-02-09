package com.CQRSArchitecture.ProductService.core.api.events;

import com.CQRSArchitecture.ProductService.core.api.repository.ProductDB;
import com.CQRSArchitecture.ProductService.core.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventHandler
    public void handlingEvent(ProductCreatedEvent event){

        ProductDB productDB = new ProductDB();

        BeanUtils.copyProperties(event, productDB);
        productRepository.save(productDB);
    }
}
