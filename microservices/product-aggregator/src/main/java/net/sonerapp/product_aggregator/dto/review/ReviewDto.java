package net.sonerapp.product_aggregator.dto.review;

import java.util.UUID;

public record ReviewDto(UUID reviewId, UUID productId, String author, String subject, String content) {

}
