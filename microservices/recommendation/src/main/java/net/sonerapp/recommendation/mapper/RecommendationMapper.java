package net.sonerapp.recommendation.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.entity.Recommendation;

@Mapper(componentModel = "spring")
public interface RecommendationMapper extends Converter<Recommendation, RecommendationDto> {

    RecommendationDto convert(Recommendation recommendation);

}
