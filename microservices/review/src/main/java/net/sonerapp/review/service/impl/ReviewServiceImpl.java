package net.sonerapp.review.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.review.dto.CreateReviewDto;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.dto.UpdateReviewDto;
import net.sonerapp.review.entity.Review;
import net.sonerapp.review.exception.NotFoundException;
import net.sonerapp.review.repository.ReviewRepository;
import net.sonerapp.review.service.ReviewService;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ConversionService conversionService;

    @Override
    public List<ReviewDto> getProductReviews(UUID productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(review -> conversionService.convert(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto createReview(CreateReviewDto createReviewDto) {
        Review review = new Review(createReviewDto.productId(), createReviewDto.author(), createReviewDto.subject(),
                createReviewDto.content());
        while (reviewRepository.existsByReviewId(review.getReviewId())) {
            review.setReviewId(UUID.randomUUID());
        }
        Review savedReview = reviewRepository.save(review);
        return conversionService.convert(savedReview, ReviewDto.class);

    }

    @Override
    public void deleteReview(UUID reviewId) {
        int deletedEntityCount = reviewRepository.deleteByReviewId(reviewId);
        if (deletedEntityCount < 1) {
            throw new NotFoundException("No review found for id: " + reviewId.toString());
        }

    }

    @Override
    public List<ReviewDto> getReviewPage(Pageable pageable) {
        return reviewRepository.findAll(pageable).stream()
                .map(review -> conversionService.convert(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateReview(UpdateReviewDto updateReviewDto, UUID reviewId) {
        Review review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new NotFoundException("No review found for id: " + reviewId.toString()));
        review.setAuthor(updateReviewDto.author());
        review.setContent(updateReviewDto.content());
        review.setSubject(updateReviewDto.subject());

        Review updatedReview = reviewRepository.save(review);
        return conversionService.convert(updatedReview, ReviewDto.class);
    }

}
