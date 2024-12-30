package net.sonerapp.product_aggregator.service;

import java.util.UUID;

import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ModifyProductDto;
import net.sonerapp.product_aggregator.dto.recommendation.ModifyRecommendationDto;
import net.sonerapp.product_aggregator.dto.review.ModifyReviewDto;

public interface ProductAggregatorService {

    ProductAggregatorDto aggregateProduct(UUID productId, String correlationId);

    void createProduct(ModifyProductDto modifyProductDto, String correlationId);

    void updateProduct(ModifyProductDto modifyProductDto, String correlationId, UUID productId);

    void deleteProduct(String correlationId, UUID productId);

    void createRecommendation(ModifyRecommendationDto modifyRecommendationDto, String correlationId, UUID productId);

    void updateRecommendation(ModifyRecommendationDto modifyRecommendationDto, String correlationId,
            UUID recommendationId);

    void deleteRecommendation(String correlationId, UUID recommendationId);

    void createReview(ModifyReviewDto modifyReviewDto, String correlationId, UUID productId);

    void updateReview(ModifyReviewDto modifyReviewDto, String correlationId,
            UUID reviewId);

    void deleteReview(String correlationId, UUID reviewId);

}
