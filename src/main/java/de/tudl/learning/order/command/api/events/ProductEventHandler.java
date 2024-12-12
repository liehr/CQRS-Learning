package de.tudl.learning.order.command.api.events;

import de.tudl.learning.order.command.api.data.Product;
import de.tudl.learning.order.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ProcessingGroup("product")
public class ProductEventHandler
{
    private final ProductRepository repository;

    public ProductEventHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event)
    {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);

        repository.save(product);
    }

    @ExceptionHandler
    public void handle(Exception e) throws Exception
    {
        throw e;
    }
}
