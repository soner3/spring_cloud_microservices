package net.sonerapp.product_aggregator.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ModifyProductDto;
import net.sonerapp.product_aggregator.dto.recommendation.ModifyRecommendationDto;
import net.sonerapp.product_aggregator.dto.review.ModifyReviewDto;

public interface ProductAggregatorController {
        @GetMapping(value = "/product/{productId}")
        ResponseEntity<ProductAggregatorDto> getProduct(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @PathVariable UUID productId);

        @PostMapping(value = "/product")
        ResponseEntity<Void> createProduct(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @RequestBody @Valid ModifyProductDto modifyProductDto);

        @PutMapping(value = "/product/{productId}")
        ResponseEntity<Void> updateProduct(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @RequestBody @Valid ModifyProductDto modifyProductDto, @PathVariable UUID productId);

        @DeleteMapping(value = "/product/{productId}")
        ResponseEntity<Void> deleteProduct(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @PathVariable UUID productId);

        @PostMapping(value = "/recommendation/{productId}")
        ResponseEntity<Void> createRecommendation(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @RequestBody @Valid ModifyRecommendationDto modifyRecommendationDto,
                        @PathVariable UUID productId);

        @PutMapping(value = "/recommendation/{recommendationId}")
        ResponseEntity<Void> updateRecommendation(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @RequestBody @Valid ModifyRecommendationDto modifyRecommendationDto,
                        @PathVariable UUID recommendationId);

        @DeleteMapping(value = "/recommendation/{recommendationId}")
        ResponseEntity<Void> deleteRecommendation(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @PathVariable UUID recommendationId);

        @PostMapping(value = "/review/{productId}")
        ResponseEntity<Void> createReview(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @RequestBody @Valid ModifyReviewDto modifyReviewDto,
                        @PathVariable UUID productId);

        @PutMapping(value = "/review/{reviewId}")
        ResponseEntity<Void> updateReview(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @RequestBody @Valid ModifyReviewDto modifyReviewDto,
                        @PathVariable UUID reviewId);

        @DeleteMapping(value = "/review/{reviewId}")
        ResponseEntity<Void> deleteReview(@RequestHeader("sonerapp-correlation-id") String correlationId,
                        @PathVariable UUID reviewId);
}
