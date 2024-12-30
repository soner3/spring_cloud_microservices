package net.sonerapp.recommendation.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.recommendation.dto.ModifyRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.entity.Recommendation;
import net.sonerapp.recommendation.exception.NotFoundException;
import net.sonerapp.recommendation.repository.RecommendationRepository;
import net.sonerapp.recommendation.service.RecommendationService;

@Service
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    private final ConversionService conversionService;

    @Override
    public List<RecommendationDto> getProductRecommendation(UUID productId) {
        return recommendationRepository
                .findByProductId(productId).stream()
                .map(recommendation -> conversionService.convert(recommendation, RecommendationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecommendationDto createRecommendation(ModifyRecommendationDto modifyRecommendationDto, UUID productId) {
        Recommendation recommendation = new Recommendation(productId,
                modifyRecommendationDto.author(), modifyRecommendationDto.rate(), modifyRecommendationDto.content());
        while (recommendationRepository.existsByRecommendationId(recommendation.getRecommendationId())) {
            recommendation.setRecommendationId(UUID.randomUUID());
        }
        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return conversionService.convert(savedRecommendation, RecommendationDto.class);

    }

    @Override
    public List<RecommendationDto> getRecommendationPage(Pageable pageable) {
        return recommendationRepository.findAll(pageable).stream()
                .map(recommendation -> conversionService.convert(recommendation, RecommendationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecommendationDto updateRecommendation(ModifyRecommendationDto modifyRecommendationDto,
            UUID recommendationId) {
        Recommendation recommendation = recommendationRepository.findByRecommendationId(recommendationId).orElseThrow(
                () -> new NotFoundException("No Recommendation found for id: " + recommendationId.toString()));
        recommendation.setAuthor(modifyRecommendationDto.author());
        recommendation.setRate(modifyRecommendationDto.rate());
        recommendation.setContent(modifyRecommendationDto.content());
        Recommendation updatedRecommendation = recommendationRepository.save(recommendation);
        return conversionService.convert(updatedRecommendation, RecommendationDto.class);
    }

    @Override
    public void deleteRecommendation(UUID recommendationId) {
        int deletedEntityCount = recommendationRepository.deleteByRecommendationId(recommendationId);

        if (deletedEntityCount < 1) {
            throw new NotFoundException("No Recommendation found for id: " + recommendationId.toString());
        }
    }

}
