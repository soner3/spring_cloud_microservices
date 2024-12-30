package net.sonerapp.product_aggregator.dto.review;

import java.util.Optional;
import java.util.UUID;

public record ReviewEventDto(Optional<ModifyReviewDto> modifyReviewDto, Optional<UUID> entityId) {

}
