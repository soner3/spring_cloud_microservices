package net.sonerapp.recommendation.dto;

import java.util.Optional;
import java.util.UUID;

public record RecommendationEventDto(Optional<ModifyRecommendationDto> modifyRecommendationDto,
        Optional<UUID> entityId) {

}
