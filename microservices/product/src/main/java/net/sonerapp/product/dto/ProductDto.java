package net.sonerapp.product.dto;

import java.util.UUID;

public record ProductDto(
        UUID productId,
        String name,
        int weight,
        double price) {
}
