package net.sonerapp.recommendation.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sonerapp.recommendation.controller.RecommendationController;
import net.sonerapp.recommendation.dto.CreateRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.dto.UpdateRecommendationDto;
import net.sonerapp.recommendation.service.RecommendationService;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
public class RecommendationControllerImpl implements RecommendationController {

    private final RecommendationService recommendationService;

    @Override
    public ResponseEntity<List<RecommendationDto>> getProductRecommendation(UUID productId) {
        List<RecommendationDto> list = recommendationService.getProductRecommendation(productId);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<RecommendationDto>> getRecommendationPage(Pageable pageable) {
        List<RecommendationDto> recommendationDtoPage = recommendationService.getRecommendationPage(pageable);
        return ResponseEntity.ok(recommendationDtoPage);
    }

    @Override
    public ResponseEntity<RecommendationDto> createRecommendation(
            @Valid @RequestBody CreateRecommendationDto createRecommendationDto) {
        RecommendationDto recommendationDto = recommendationService.createRecommendation(createRecommendationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendationDto);
    }

    @Override
    public ResponseEntity<RecommendationDto> updateRecommendation(
            @Valid UpdateRecommendationDto updateRecommendationDto, UUID recommendationId) {
        RecommendationDto recommendationDto = recommendationService.updateRecommendation(updateRecommendationDto,
                recommendationId);
        return ResponseEntity.ok(recommendationDto);
    }

    @Override
    public ResponseEntity<Void> deleteRecommendation(UUID recommendationId) {
        recommendationService.deleteRecommendation(recommendationId);
        return ResponseEntity.noContent().build();
    }

}
