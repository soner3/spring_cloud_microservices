package net.sonerapp.review.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sonerapp.review.controller.ReviewController;
import net.sonerapp.review.dto.CreateReviewDto;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.dto.UpdateReviewDto;
import net.sonerapp.review.service.ReviewService;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Review", description = "Endpoints for managing product reviews")
public class ReviewControllerImpl implements ReviewController {

  private final ReviewService reviewService;

  @Override
  @Operation(summary = "Get reviews for a product", description = "Fetches all reviews associated with a specific product using its productId.", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved product reviews", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto[].class))),
      @ApiResponse(responseCode = "403", description = "Forbidden - user doesn't have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  public ResponseEntity<List<ReviewDto>> getProductReviews(UUID productId) {
    List<ReviewDto> reviewDtoList = reviewService.getProductReviews(productId);
    return ResponseEntity.ok(reviewDtoList);
  }

  @Override
  @Operation(summary = "Create a new review", description = "Creates a new review for a product based on the provided review details.", responses = {
      @ApiResponse(responseCode = "201", description = "Successfully created the review", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request - invalid review data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  public ResponseEntity<ReviewDto> createReview(@Valid CreateReviewDto createReviewDto) {
    ReviewDto reviewDto = reviewService.createReview(createReviewDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
  }

  @Override
  @Operation(summary = "Delete a review", description = "Deletes a review by its reviewId.", responses = {
      @ApiResponse(responseCode = "204", description = "Successfully deleted the review", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden - user doesn't have access to delete the review", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "Not Found - review not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  public ResponseEntity<Void> deleteReview(UUID reviewId) {
    reviewService.deleteReview(reviewId);
    return ResponseEntity.noContent().build();
  }

  @Override
  @Operation(summary = "Get paginated reviews", description = "Fetches reviews in a paginated manner based on the provided Pageable object.", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated reviews", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto[].class)))
  })
  public ResponseEntity<List<ReviewDto>> getReviewPage(Pageable pageable) {
    List<ReviewDto> reviewDtoList = reviewService.getReviewPage(pageable);
    return ResponseEntity.ok(reviewDtoList);
  }

  @Override
  @Operation(summary = "Update a review", description = "Updates an existing review by its reviewId based on the provided updated review data.", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully updated the review", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request - invalid review data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden - user doesn't have access to update the review", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "Not Found - review not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  public ResponseEntity<ReviewDto> updateReview(@Valid UpdateReviewDto updateReviewDto, UUID reviewId) {
    ReviewDto reviewDto = reviewService.updateReview(updateReviewDto, reviewId);
    return ResponseEntity.ok(reviewDto);
  }

}
