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
import net.sonerapp.product_aggregator.dto.recommendation.RecommendationDto;
import net.sonerapp.product_aggregator.dto.review.ReviewDto;
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

    @Override
    public ProductAggregatorDto aggregateProduct(UUID productId, String correlationId) {
        log.debug("Correlation-id found: {}", correlationId);
        ResponseEntity<ProductDto> productResponse = productService.getProduct(productId, correlationId);
        ResponseEntity<List<RecommendationDto>> recommendationReponse = recommendationService
                .getProductRecommendation(productId, correlationId);
        ResponseEntity<List<ReviewDto>> reviewResponse = reviewService.getProductReviews(productId, correlationId);
        return new ProductAggregatorDto(
                productResponse.getBody(),
                recommendationReponse.getBody(),
                reviewResponse.getBody());

    }

    @Override
    public void createProduct(ModifyProductDto modifyProductDto, String correlationId) {
        log.debug("Correlation-id found: {}", correlationId);
        ProductEventDto productEventDto = new ProductEventDto(Optional.of(modifyProductDto), Optional.ofNullable(null));
        Event<ProductEventDto, String> event = new Event<ProductEventDto, String>(Event.Type.CREATE, productEventDto,
                correlationId);
        log.debug("Sending message to product service: {}", event);
        boolean maybeSuccess = streamBridge.send("productHandler-out-0", event);
        log.info("Sending status: {}", maybeSuccess);
    }

}
