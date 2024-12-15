package net.sonerapp.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sonerapp.review.dto.ReviewDto;

public interface ReviewController {

    @GetMapping(value = "/review/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable int productId);

}