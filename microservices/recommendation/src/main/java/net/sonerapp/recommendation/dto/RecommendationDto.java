package net.sonerapp.recommendation.dto;

import java.util.UUID;

public record RecommendationDto(UUID recommendationId, UUID productId, String author, int rate, String content) {

}
