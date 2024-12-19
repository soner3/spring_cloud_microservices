package net.sonerapp.recommendation.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.entity.Recommendation;

@Component
public class ConversionServiceAdapter {

    private final ConversionService conversionService;

    public ConversionServiceAdapter(@Lazy final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public RecommendationDto mapRecommendationToRecommendationDto(final Recommendation recommendation) {
        return (RecommendationDto) conversionService.convert(recommendation,
                TypeDescriptor.valueOf(Recommendation.class), TypeDescriptor.valueOf(RecommendationDto.class));
    }

}
