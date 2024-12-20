package net.sonerapp.review.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.review.dto.CreateReviewDto;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.dto.UpdateReviewDto;

public interface ReviewService {

    List<ReviewDto> getProductReviews(UUID productId);

    ReviewDto createReview(CreateReviewDto createReviewDto);

    List<ReviewDto> getReviewPage(Pageable pageable);

    ReviewDto updateReview(UpdateReviewDto updateReviewDto, UUID reviewId);

    @Transactional
    void deleteReview(UUID reviewId);

}
