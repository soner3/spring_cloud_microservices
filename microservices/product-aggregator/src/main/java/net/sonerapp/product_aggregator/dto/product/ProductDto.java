package net.sonerapp.product_aggregator.dto.product;

import java.util.UUID;

public record ProductDto(
                UUID productId,
                String name,
                int weight,
                double price) {
}