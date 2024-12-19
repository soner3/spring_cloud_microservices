package net.sonerapp.recommendation.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.exception.InvalidInputException;
import net.sonerapp.recommendation.repository.RecommendationRepository;
import net.sonerapp.recommendation.service.RecommendationService;

@Service
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    private final ConversionService conversionService;

    @Override
    public List<RecommendationDto> getRecommendations(int productId) {

        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        return recommendationRepository
                .findByProductId(productId).stream()
                .map(recommendation -> conversionService.convert(recommendation, RecommendationDto.class))
                .collect(Collectors.toList());

    }

}
