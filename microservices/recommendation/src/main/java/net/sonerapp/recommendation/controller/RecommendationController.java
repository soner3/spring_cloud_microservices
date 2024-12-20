package net.sonerapp.recommendation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import net.sonerapp.recommendation.dto.CreateRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.dto.UpdateRecommendationDto;

public interface RecommendationController {

        @GetMapping("/recommendation/{productId}")
        public ResponseEntity<List<RecommendationDto>> getProductRecommendation(@PathVariable UUID productId);

        @GetMapping("/recommendation")
        public ResponseEntity<List<RecommendationDto>> getRecommendationPage(Pageable pageable);

        @PostMapping("/recommendation")
        public ResponseEntity<RecommendationDto> createRecommendation(
                        @RequestBody @Valid CreateRecommendationDto createRecommendationDto);

        @PutMapping("/recommendation/{recommendationId}")
        public ResponseEntity<RecommendationDto> updateRecommendation(
                        @RequestBody @Valid UpdateRecommendationDto updateRecommendationDto,
                        @PathVariable UUID recommendationId);

        @DeleteMapping("/recommendation/{recommendationId}")
        public ResponseEntity<Void> deleteRecommendation(@PathVariable UUID recommendationId);

}