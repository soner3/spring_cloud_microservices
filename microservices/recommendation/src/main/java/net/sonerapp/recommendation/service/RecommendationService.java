package net.sonerapp.recommendation.service;

import java.util.List;

import net.sonerapp.recommendation.dto.RecommendationDto;

public interface RecommendationService {

    List<RecommendationDto> getRecommendations(int productId);

}
