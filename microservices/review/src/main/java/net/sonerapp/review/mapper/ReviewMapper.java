package net.sonerapp.review.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends Converter<Review, ReviewDto> {

    ReviewDto convert(Review review);

}
