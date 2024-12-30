package net.sonerapp.product_aggregator.service;

import java.util.UUID;

import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ModifyProductDto;

public interface ProductAggregatorService {

    ProductAggregatorDto aggregateProduct(UUID productId, String correlationId);

    void createProduct(ModifyProductDto modifyProductDto, String correlationId);

}
