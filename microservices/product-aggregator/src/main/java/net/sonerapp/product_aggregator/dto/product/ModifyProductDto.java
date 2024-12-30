package net.sonerapp.product_aggregator.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModifyProductDto(
                @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters") @NotBlank(message = "Name must not be blank") String name,
                @Min(value = 1, message = "Weight must be at least 1") int weight,
                @Min(value = 0, message = "Price must be at least 0") double price) {

}
