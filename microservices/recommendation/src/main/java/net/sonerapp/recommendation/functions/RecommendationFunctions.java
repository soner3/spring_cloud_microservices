package net.sonerapp.recommendation.functions;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.recommendation.dto.ModifyRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationEventDto;
import net.sonerapp.recommendation.exception.InvalidEventDataException;
import net.sonerapp.recommendation.service.RecommendationService;

@Configuration
@Slf4j
public class RecommendationFunctions {

    @Bean
    Consumer<Event<RecommendationEventDto, String>> recommendationHandler(RecommendationService recommendationService) {
        return event -> {
            String correlationId = event.getKey();
            log.info("Received Message for correlation-id: {}", correlationId);
            RecommendationEventDto recommendationEventDto = event.getData();
            switch (event.getEventType()) {
                case CREATE:
                    log.info("Create recommendation for correlation-id: {}", correlationId);
                    ModifyRecommendationDto createModifyRecommendationDto = extractModifyRecommendationDto(
                            recommendationEventDto,
                            correlationId);
                    UUID createEntityId = extractEntityId(recommendationEventDto, correlationId);
                    recommendationService.createRecommendation(createModifyRecommendationDto, createEntityId);
                    break;
                case UPDATE:
                    log.info("Update recommendation for correlation-id: {}", correlationId);
                    ModifyRecommendationDto updateModifyRecommendationDto = extractModifyRecommendationDto(
                            recommendationEventDto,
                            correlationId);
                    UUID updateEntityId = extractEntityId(recommendationEventDto, correlationId);
                    recommendationService.updateRecommendation(updateModifyRecommendationDto, updateEntityId);
                    break;
                case DELETE:
                    log.info("Delete recommendation for correlation-id: {}", correlationId);
                    UUID deleteEntityId = extractEntityId(recommendationEventDto, correlationId);
                    recommendationService.deleteRecommendation(deleteEntityId);
                    break;
                default:
                    log.error("Could not handle product event for correlation-id: {}", correlationId);
                    throw new InvalidEventDataException(
                            "No valid event type has been sent for correlation-id: " + correlationId);
            }
        };

    }

    private ModifyRecommendationDto extractModifyRecommendationDto(RecommendationEventDto recommendationEventDto,
            String correlationId) {
        return recommendationEventDto
                .modifyRecommendationDto()
                .orElseThrow(() -> new InvalidEventDataException(
                        "No Modify-Recommendation-Dto found in event data for correlation-id: " + correlationId));
    }

    private UUID extractEntityId(RecommendationEventDto recommendationEventDto, String correlationId) {
        return recommendationEventDto
                .entityId()
                .orElseThrow(() -> new InvalidEventDataException(
                        "No Recommendation-Id found in event data for correlation-id: " + correlationId));
    }

}
