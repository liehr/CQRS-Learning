package de.tudl.learning.order.query.api.controller;

import de.tudl.learning.order.command.api.model.ProductRestModel;
import de.tudl.learning.order.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController
{
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }



    @GetMapping
    public List<ProductRestModel> getAllProducts()
    {
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        return queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
    }
}
