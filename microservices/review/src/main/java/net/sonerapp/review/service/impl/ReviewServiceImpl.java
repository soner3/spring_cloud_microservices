package net.sonerapp.review.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.exception.InvalidInputException;
import net.sonerapp.review.repository.ReviewRepository;
import net.sonerapp.review.service.ReviewService;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ConversionService conversionService;

    @Override
    public List<ReviewDto> getReviews(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }
        return reviewRepository.findByProductId(productId).stream()
                .map(review -> conversionService.convert(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}
