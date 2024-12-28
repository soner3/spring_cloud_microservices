package net.sonerapp.product_aggregator.service;

import java.util.UUID;

import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;

public interface ProductAggregatorService {

    ProductAggregatorDto aggregateProduct(UUID productId, String correlationId);
}
