package net.sonerapp.recommendation.dto;

public record RecommendationDto(int productId, int recommendationId, String author, int rate, String content) {

}
