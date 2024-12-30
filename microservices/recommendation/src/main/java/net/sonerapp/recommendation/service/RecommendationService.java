package net.sonerapp.recommendation.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.recommendation.dto.ModifyRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationDto;

public interface RecommendationService {

    List<RecommendationDto> getProductRecommendation(UUID productId);

    RecommendationDto createRecommendation(ModifyRecommendationDto modifyRecommendationDto, UUID productId);

    List<RecommendationDto> getRecommendationPage(Pageable pageable);

    RecommendationDto updateRecommendation(ModifyRecommendationDto modifyRecommendationDto, UUID recommendationId);

    @Transactional
    void deleteRecommendation(UUID recommendationId);

}
