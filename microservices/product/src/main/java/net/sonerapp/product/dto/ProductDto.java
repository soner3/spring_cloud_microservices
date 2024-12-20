package net.sonerapp.product.dto;

public record ProductDto(
                String productId,
                String name,
                int weight,
                double price) {
}
