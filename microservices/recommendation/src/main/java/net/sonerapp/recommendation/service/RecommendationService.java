package net.sonerapp.recommendation.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import net.sonerapp.recommendation.dto.CreateRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.dto.UpdateRecommendationDto;

public interface RecommendationService {

    List<RecommendationDto> getProductRecommendation(UUID productId);

    RecommendationDto createRecommendation(CreateRecommendationDto createRecommendationDto);

    List<RecommendationDto> getRecommendationPage(Pageable pageable);

    RecommendationDto updateRecommendation(UpdateRecommendationDto updateRecommendationDto, UUID recommendationId);

    void deleteRecommendation(UUID recommendationId);

}
