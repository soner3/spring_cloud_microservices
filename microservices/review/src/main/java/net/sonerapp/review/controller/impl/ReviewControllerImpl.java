package net.sonerapp.review.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/v1")
public class ReviewControllerImpl implements ReviewController {

  private final ReviewService reviewService;

  @Override
  public ResponseEntity<List<ReviewDto>> getProductReviews(UUID productId) {
    List<ReviewDto> reviewDtoList = reviewService.getProductReviews(productId);
    return ResponseEntity.ok(reviewDtoList);
  }

  @Override
  public ResponseEntity<ReviewDto> createReview(@Valid CreateReviewDto createReviewDto) {
    ReviewDto reviewDto = reviewService.createReview(createReviewDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
  }

  @Override
  public ResponseEntity<ReviewDto> deleteReview(UUID reviewId) {
    reviewService.deleteReview(reviewId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<List<ReviewDto>> getReviewPage(Pageable pageable) {
    List<ReviewDto> reviewDtoList = reviewService.getReviewPage(pageable);
    return ResponseEntity.ok(reviewDtoList);
  }

  @Override
  public ResponseEntity<ReviewDto> updateReview(@Valid UpdateReviewDto updateReviewDto, UUID reviewId) {
    ReviewDto reviewDto = reviewService.updateReview(updateReviewDto, reviewId);
    return ResponseEntity.ok(reviewDto);
  }

}
