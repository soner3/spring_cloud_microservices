package net.sonerapp.product_aggregator.dto.review;

public record ReviewDto(int productId, int reviewId, String author, String subject, String content) {

}
