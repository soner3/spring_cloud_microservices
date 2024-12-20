package net.sonerapp.review.dto;

import java.util.UUID;

public record ReviewDto(UUID reviewId, UUID productId, String author, String subject, String content) {

}
