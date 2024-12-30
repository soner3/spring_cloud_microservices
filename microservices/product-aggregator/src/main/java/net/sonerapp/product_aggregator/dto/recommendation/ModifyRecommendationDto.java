package net.sonerapp.product_aggregator.dto.recommendation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModifyRecommendationDto(
        @Size(min = 2, max = 255, message = "Author name must be between 2 and 255 characters.") String author,
        @Min(value = 0, message = "Rate must be at least 0.") @Max(value = 5, message = "Rate must be at most 5.") int rate,
        @NotBlank(message = "Content must not be blank.") @Size(min = 2, max = 255, message = "Content must be between 2 and 255 characters.") String content) {

}
