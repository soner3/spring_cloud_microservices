package net.sonerapp.recommendation.dto;

public record RecommendationDto(int recommendationId, int productId, String author, int rate, String content) {

}
