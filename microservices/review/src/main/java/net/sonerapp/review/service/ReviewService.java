package net.sonerapp.review.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.review.dto.ModifyReviewDto;
import net.sonerapp.review.dto.ReviewDto;

public interface ReviewService {

    List<ReviewDto> getProductReviews(UUID productId);

    ReviewDto createReview(ModifyReviewDto modifyReviewDto, UUID productId);

    List<ReviewDto> getReviewPage(Pageable pageable);

    ReviewDto updateReview(ModifyReviewDto modifyReviewDto, UUID reviewId);

    @Transactional
    void deleteReview(UUID reviewId);

}
