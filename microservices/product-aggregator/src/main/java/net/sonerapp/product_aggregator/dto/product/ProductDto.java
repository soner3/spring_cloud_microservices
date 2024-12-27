package net.sonerapp.product_aggregator.dto.product;

public record ProductDto(
        String productId,
        String name,
        int weight,
        double price) {
}