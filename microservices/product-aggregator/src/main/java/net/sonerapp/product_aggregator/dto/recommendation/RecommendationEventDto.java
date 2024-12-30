package net.sonerapp.product_aggregator.dto.recommendation;

import java.util.Optional;
import java.util.UUID;

public record RecommendationEventDto(Optional<ModifyRecommendationDto> modifyRecommendationDto,
                Optional<UUID> entityId) {

}
