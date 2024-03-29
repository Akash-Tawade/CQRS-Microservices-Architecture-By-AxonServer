package com.CQRSArchitecture.ProductService.query.api.controller;

import com.CQRSArchitecture.ProductService.core.api.model.ProductRestModel;
import com.CQRSArchitecture.ProductService.query.api.queries.GetProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public  ProductQueryController(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts(){

        GetProductQuery getProductQuery = new GetProductQuery();
        List<ProductRestModel> productRestModels =
        queryGateway.query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return productRestModels;
    }
}
