package net.sonerapp.review.dto;

import java.util.Optional;
import java.util.UUID;

public record ReviewEventDto(Optional<ModifyReviewDto> modifyReviewDto, Optional<UUID> entityId) {

}
