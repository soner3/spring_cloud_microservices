package net.sonerapp.review.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.entity.Review;

@Component
public class ConversionServiceAdapter {

    private final ConversionService conversionService;

    public ConversionServiceAdapter(@Lazy final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public ReviewDto mapReviewToReviewDto(final Review review) {
        return (ReviewDto) conversionService.convert(review, TypeDescriptor.valueOf(Review.class),
                TypeDescriptor.valueOf(ReviewDto.class));
    }

}
