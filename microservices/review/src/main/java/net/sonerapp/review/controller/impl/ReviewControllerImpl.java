package net.sonerapp.review.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.review.controller.ReviewController;
import net.sonerapp.review.dto.ReviewDto;
import net.sonerapp.review.exception.InvalidInputException;

@RestController
@Slf4j
public class ReviewControllerImpl implements ReviewController {

  @Override
  public ResponseEntity<List<ReviewDto>> getReviews(int productId) {
    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    if (productId == 213) {
      log.debug("No Reviews found for productId: {}", productId);
      return ResponseEntity.ok(new ArrayList<>());
    }

    List<ReviewDto> list = new ArrayList<>();
    list.add(new ReviewDto(productId, 1, "Author 1", "Subject 1", "Content 1"));
    list.add(new ReviewDto(productId, 2, "Author 2", "Subject 2", "Content 2"));
    list.add(new ReviewDto(productId, 3, "Author 3", "Subject 3", "Content 3"));

    log.debug("/Reviews response size: {}", list.size());

    return ResponseEntity.ok(list);
  }

}
