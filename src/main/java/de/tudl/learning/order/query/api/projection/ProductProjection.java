package de.tudl.learning.order.query.api.projection;

import de.tudl.learning.order.command.api.data.Product;
import de.tudl.learning.order.command.api.data.ProductRepository;
import de.tudl.learning.order.command.api.model.ProductRestModel;
import de.tudl.learning.order.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection
{
    private ProductRepository repository;

    public ProductProjection(ProductRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery)
    {
        List<Product> products = repository.findAll();

        return products
                .stream()
                .map(product ->
                        ProductRestModel.
                                builder()
                                .name(product.getName())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build()
                )
                .toList();
    }
}
