package net.sonerapp.review.dto;

public record ReviewDto(int productId, int reviewId, String author, String subject, String content) {

}
