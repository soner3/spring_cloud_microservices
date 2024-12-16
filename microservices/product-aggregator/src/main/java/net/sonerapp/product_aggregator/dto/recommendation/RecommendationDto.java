package net.sonerapp.product_aggregator.dto.recommendation;

public record RecommendationDto(int productId, int recommendationId, String author, int rate, String content) {

}
