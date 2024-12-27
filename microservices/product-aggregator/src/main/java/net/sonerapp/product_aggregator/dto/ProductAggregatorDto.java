package net.sonerapp.product_aggregator.dto;

import java.util.List;

import net.sonerapp.product_aggregator.dto.product.ProductDto;
import net.sonerapp.product_aggregator.dto.recommendation.RecommendationDto;
import net.sonerapp.product_aggregator.dto.review.ReviewDto;

public record ProductAggregatorDto(
        ProductDto product,
        List<RecommendationDto> recommendations,
        List<ReviewDto> reviews) {

}
