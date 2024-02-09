package com.CQRSArchitecture.ProductService.command.api.aggregate;

import com.CQRSArchitecture.ProductService.command.api.commands.CreateProductCommand;
import com.CQRSArchitecture.ProductService.core.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public  ProductAggregate(){
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
        //you can perform all the validations

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        //It will copy all the property value from createProductCommand to productCreatedEvent
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        //It will publish particular event.
        AggregateLifecycle.apply(productCreatedEvent);
    }

    //Event Listen
    @EventSourcingHandler
    public void onHandler(ProductCreatedEvent productCreatedEvent){
        this.name = productCreatedEvent.getName();
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }
}
