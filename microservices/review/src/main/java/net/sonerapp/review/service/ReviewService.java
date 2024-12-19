package net.sonerapp.review.service;

import java.util.List;

import net.sonerapp.review.dto.ReviewDto;

public interface ReviewService {

    List<ReviewDto> getReviews(int productId);

}
