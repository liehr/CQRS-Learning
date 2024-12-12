package de.tudl.learning.order.command.api.controller;

import de.tudl.learning.order.command.api.commands.CreateProductCommand;
import de.tudl.learning.order.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(("/products"))
public class ProductCommandController
{
    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel model)
    {
        CreateProductCommand createProductCommand = CreateProductCommand
                .builder()
                .productId(UUID.randomUUID().toString())
                .name(model.getName())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();

        return commandGateway.sendAndWait(createProductCommand);
    }
}
