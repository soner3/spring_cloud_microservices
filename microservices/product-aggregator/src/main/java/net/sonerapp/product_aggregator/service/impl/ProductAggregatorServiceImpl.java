package net.sonerapp.product_aggregator.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ProductDto;
import net.sonerapp.product_aggregator.dto.recommendation.RecommendationDto;
import net.sonerapp.product_aggregator.dto.review.ReviewDto;
import net.sonerapp.product_aggregator.service.ProductAggregatorService;
import net.sonerapp.product_aggregator.service.client.ProductService;
import net.sonerapp.product_aggregator.service.client.RecommendationService;
import net.sonerapp.product_aggregator.service.client.ReviewService;

@AllArgsConstructor
@Service
public class ProductAggregatorServiceImpl implements ProductAggregatorService {

    private final ProductService productService;
    private final RecommendationService recommendationService;
    private final ReviewService reviewService;

    @Override
    public ProductAggregatorDto aggregateProduct(UUID productId, String correlationId) {
        ResponseEntity<ProductDto> productResponse = productService.getProduct(productId, correlationId);
        ResponseEntity<List<RecommendationDto>> recommendationReponse = recommendationService
                .getProductRecommendation(productId, correlationId);
        ResponseEntity<List<ReviewDto>> reviewResponse = reviewService.getProductReviews(productId, correlationId);
        return new ProductAggregatorDto(
                productResponse.getBody(),
                recommendationReponse.getBody(),
                reviewResponse.getBody());

    }

}
