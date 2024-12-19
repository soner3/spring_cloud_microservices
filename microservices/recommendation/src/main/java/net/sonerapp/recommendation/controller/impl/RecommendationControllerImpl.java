package net.sonerapp.recommendation.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sonerapp.recommendation.controller.RecommendationController;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.service.RecommendationService;

@RestController
@Slf4j
@AllArgsConstructor
public class RecommendationControllerImpl implements RecommendationController {

    private final RecommendationService recommendationService;

    @Override
    public ResponseEntity<List<RecommendationDto>> getRecommendations(int productId) {
        List<RecommendationDto> list = recommendationService.getRecommendations(productId);
        return ResponseEntity.ok(list);
    }

}
