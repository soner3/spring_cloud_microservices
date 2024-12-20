package net.sonerapp.product.dto;

public record CreateProductDto(
        String name,
        int weight,
        double price) {

}
