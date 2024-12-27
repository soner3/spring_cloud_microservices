package net.sonerapp.product_aggregator.dto.recommendation;

import java.util.UUID;

public record RecommendationDto(UUID recommendationId, UUID productId, String author, int rate, String content) {

}
