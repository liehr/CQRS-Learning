package de.tudl.learning.order.command.api.aggregate;

import de.tudl.learning.order.command.api.commands.CreateProductCommand;
import de.tudl.learning.order.command.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate
{
    @AggregateIdentifier
    private String productId;

    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand)
    {
        ProductCreatedEvent createdEvent = new ProductCreatedEvent();

        BeanUtils.copyProperties(createProductCommand, createdEvent);

        AggregateLifecycle.apply(createdEvent);
    }

    public ProductAggregate()
    {

    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent createdEvent)
    {
        this.productId = createdEvent.getProductId();
        this.name = createdEvent.getName();
        this.price = createdEvent.getPrice();
        this.quantity = createdEvent.getQuantity();
    }
}
