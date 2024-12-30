package net.sonerapp.review.functions;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.review.dto.ModifyReviewDto;
import net.sonerapp.review.dto.ReviewEventDto;
import net.sonerapp.review.exception.InvalidEventDataException;
import net.sonerapp.review.service.ReviewService;

@Configuration
@Slf4j
public class ReviewFunctions {

    @Bean
    Consumer<Event<ReviewEventDto, String>> reviewHandler(ReviewService reviewService) {
        return event -> {
            String correlationId = event.getKey();
            log.info("Received Message for correlation-id: {}", correlationId);
            ReviewEventDto reviewEventDto = event.getData();
            switch (event.getEventType()) {
                case CREATE:
                    log.info("Create recommendation for correlation-id: {}", correlationId);
                    ModifyReviewDto createModifyReviewDto = extractModifyReviewDto(
                            reviewEventDto,
                            correlationId);
                    UUID createEntityId = extractEntityId(reviewEventDto, correlationId);
                    reviewService.createReview(createModifyReviewDto, createEntityId);
                    break;
                case UPDATE:
                    log.info("Update recommendation for correlation-id: {}", correlationId);
                    ModifyReviewDto updateModifyReviewDto = extractModifyReviewDto(
                            reviewEventDto,
                            correlationId);
                    UUID updateEntityId = extractEntityId(reviewEventDto, correlationId);
                    reviewService.updateReview(updateModifyReviewDto, updateEntityId);
                    break;
                case DELETE:
                    log.info("Delete recommendation for correlation-id: {}", correlationId);
                    UUID deleteEntityId = extractEntityId(reviewEventDto, correlationId);
                    reviewService.deleteReview(deleteEntityId);
                    break;
                default:
                    log.error("Could not handle product event for correlation-id: {}", correlationId);
                    throw new InvalidEventDataException(
                            "No valid event type has been sent for correlation-id: " + correlationId);
            }
        };

    }

    private ModifyReviewDto extractModifyReviewDto(ReviewEventDto reviewEventDto,
            String correlationId) {
        return reviewEventDto
                .modifyReviewDto()
                .orElseThrow(() -> new InvalidEventDataException(
                        "No Modify-Recommendation-Dto found in event data for correlation-id: " + correlationId));
    }

    private UUID extractEntityId(ReviewEventDto reviewEventDto, String correlationId) {
        return reviewEventDto
                .entityId()
                .orElseThrow(() -> new InvalidEventDataException(
                        "No Recommendation-Id found in event data for correlation-id: " + correlationId));
    }

}
