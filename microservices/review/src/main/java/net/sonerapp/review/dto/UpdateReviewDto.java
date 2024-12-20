package net.sonerapp.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateReviewDto(
        @Size(min = 2, max = 255, message = "Author name must be between 2 and 255 characters.") String author,
        @Size(min = 2, max = 255, message = "Subject must be between 2 and 255 characters.") String subject,
        @NotBlank(message = "Content must not be blank.") @Size(min = 2, max = 255, message = "Content must be between 2 and 255 characters.") String content) {

}
