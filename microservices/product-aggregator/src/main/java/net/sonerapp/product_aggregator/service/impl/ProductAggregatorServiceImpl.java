package net.sonerapp.product_aggregator.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ModifyProductDto;
import net.sonerapp.product_aggregator.dto.product.ProductDto;
import net.sonerapp.product_aggregator.dto.product.ProductEventDto;
import net.sonerapp.product_aggregator.dto.recommendation.ModifyRecommendationDto;
import net.sonerapp.product_aggregator.dto.recommendation.RecommendationDto;
import net.sonerapp.product_aggregator.dto.recommendation.RecommendationEventDto;
import net.sonerapp.product_aggregator.dto.review.ModifyReviewDto;
import net.sonerapp.product_aggregator.dto.review.ReviewDto;
import net.sonerapp.product_aggregator.dto.review.ReviewEventDto;
import net.sonerapp.product_aggregator.functions.Event;
import net.sonerapp.product_aggregator.service.ProductAggregatorService;
import net.sonerapp.product_aggregator.service.client.ProductService;
import net.sonerapp.product_aggregator.service.client.RecommendationService;
import net.sonerapp.product_aggregator.service.client.ReviewService;

@AllArgsConstructor
@Service
@Slf4j
public class ProductAggregatorServiceImpl implements ProductAggregatorService {

        private final ProductService productService;
        private final RecommendationService recommendationService;
        private final ReviewService reviewService;
        private final StreamBridge streamBridge;

        private final String PRODUCT_BINDING_NAME = "productHandler-out-0";
        private final String RECOMMENDATION_BINDING_NAME = "recommendationHandler-out-0";
        private final String REVIEW_BINDING_NAME = "reviewHandler-out-0";

        @Override
        public ProductAggregatorDto aggregateProduct(UUID productId, String correlationId) {
                log.info("Correlation-id found: {}", correlationId);
                ResponseEntity<ProductDto> productResponse = productService.getProduct(productId, correlationId);
                ResponseEntity<List<RecommendationDto>> recommendationReponse = recommendationService
                                .getProductRecommendation(productId, correlationId);
                ResponseEntity<List<ReviewDto>> reviewResponse = reviewService.getProductReviews(productId,
                                correlationId);
                return new ProductAggregatorDto(
                                productResponse.getBody(),
                                recommendationReponse.getBody(),
                                reviewResponse.getBody());

        }

        @Override
        public void createProduct(ModifyProductDto modifyProductDto, String correlationId) {
                log.info("Correlation-id found: {}", correlationId);
                ProductEventDto productEventDto = new ProductEventDto(Optional.of(modifyProductDto),
                                Optional.ofNullable(null));
                Event<ProductEventDto, String> event = new Event<ProductEventDto, String>(Event.Type.CREATE,
                                productEventDto,
                                correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(PRODUCT_BINDING_NAME, event);
        }

        @Override
        public void deleteProduct(String correlationId, UUID productId) {
                log.info("Correlation-id found: {}", correlationId);
                ProductEventDto productEventDto = new ProductEventDto(Optional.ofNullable(null),
                                Optional.of(productId));
                Event<ProductEventDto, String> event = new Event<ProductEventDto, String>(Event.Type.DELETE,
                                productEventDto,
                                correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(PRODUCT_BINDING_NAME, event);

        }

        @Override
        public void updateProduct(ModifyProductDto modifyProductDto, String correlationId, UUID productId) {
                log.info("Correlation-id found: {}", correlationId);
                ProductEventDto productEventDto = new ProductEventDto(Optional.of(modifyProductDto),
                                Optional.of(productId));
                Event<ProductEventDto, String> event = new Event<ProductEventDto, String>(Event.Type.UPDATE,
                                productEventDto,
                                correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(PRODUCT_BINDING_NAME, event);

        }

        @Override
        public void createRecommendation(ModifyRecommendationDto modifyRecommendationDto, String correlationId,
                        UUID productId) {
                log.info("Correlation-id found: {}", correlationId);
                RecommendationEventDto recommendationEventDto = new RecommendationEventDto(
                                Optional.of(modifyRecommendationDto),
                                Optional.of(productId));
                Event<RecommendationEventDto, String> event = new Event<RecommendationEventDto, String>(
                                Event.Type.CREATE,
                                recommendationEventDto, correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(RECOMMENDATION_BINDING_NAME, event);
        }

        @Override
        public void deleteRecommendation(String correlationId, UUID recommendationId) {
                log.info("Correlation-id found: {}", correlationId);
                RecommendationEventDto recommendationEventDto = new RecommendationEventDto(Optional.ofNullable(null),
                                Optional.of(recommendationId));
                Event<RecommendationEventDto, String> event = new Event<RecommendationEventDto, String>(
                                Event.Type.DELETE,
                                recommendationEventDto, correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(RECOMMENDATION_BINDING_NAME, event);
        }

        @Override
        public void updateRecommendation(ModifyRecommendationDto modifyRecommendationDto, String correlationId,
                        UUID recommendationId) {
                log.info("Correlation-id found: {}", correlationId);
                RecommendationEventDto recommendationEventDto = new RecommendationEventDto(
                                Optional.of(modifyRecommendationDto),
                                Optional.of(recommendationId));
                Event<RecommendationEventDto, String> event = new Event<RecommendationEventDto, String>(
                                Event.Type.UPDATE,
                                recommendationEventDto, correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(RECOMMENDATION_BINDING_NAME, event);
        }

        @Override
        public void createReview(ModifyReviewDto modifyReviewDto, String correlationId, UUID productId) {
                log.info("Correlation-id found: {}", correlationId);
                ReviewEventDto reviewEventDto = new ReviewEventDto(
                                Optional.of(modifyReviewDto),
                                Optional.of(productId));
                Event<ReviewEventDto, String> event = new Event<ReviewEventDto, String>(
                                Event.Type.CREATE,
                                reviewEventDto, correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(REVIEW_BINDING_NAME, event);

        }

        @Override
        public void deleteReview(String correlationId, UUID reviewId) {
                log.info("Correlation-id found: {}", correlationId);
                ReviewEventDto reviewEventDto = new ReviewEventDto(Optional.ofNullable(null),
                                Optional.of(reviewId));
                Event<ReviewEventDto, String> event = new Event<ReviewEventDto, String>(
                                Event.Type.DELETE,
                                reviewEventDto, correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(REVIEW_BINDING_NAME, event);

        }

        @Override
        public void updateReview(ModifyReviewDto modifyReviewDto, String correlationId, UUID reviewId) {
                log.info("Correlation-id found: {}", correlationId);
                ReviewEventDto reviewEventDto = new ReviewEventDto(
                                Optional.of(modifyReviewDto),
                                Optional.of(reviewId));
                Event<ReviewEventDto, String> event = new Event<ReviewEventDto, String>(
                                Event.Type.UPDATE,
                                reviewEventDto, correlationId);
                log.debug("Sending message to product service: {}", event);
                streamBridge.send(REVIEW_BINDING_NAME, event);

        }

}
