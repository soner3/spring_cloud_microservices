package net.sonerapp.review.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import net.sonerapp.review.dto.CreateReviewDto;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.dto.UpdateReviewDto;

public interface ReviewController {

    @GetMapping("/review/{productId}")
    public ResponseEntity<List<ReviewDto>> getProductReviews(@PathVariable UUID productId);

    @GetMapping("/review")
    public ResponseEntity<List<ReviewDto>> getReviewPage(Pageable pageable);

    @PostMapping("/review")
    public ResponseEntity<ReviewDto> createReview(@RequestBody @Valid CreateReviewDto createReviewDto);

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody @Valid UpdateReviewDto updateReviewDto,
            @PathVariable UUID reviewId);

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId);

}