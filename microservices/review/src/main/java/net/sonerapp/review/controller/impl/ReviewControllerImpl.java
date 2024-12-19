package net.sonerapp.review.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sonerapp.review.controller.ReviewController;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.service.ReviewService;

@RestController
@Slf4j
@AllArgsConstructor
public class ReviewControllerImpl implements ReviewController {

  private final ReviewService reviewService;

  @Override
  public ResponseEntity<List<ReviewDto>> getReviews(int productId) {
    List<ReviewDto> list = reviewService.getReviews(productId);
    return ResponseEntity.ok(list);
  }

}
