package net.sonerapp.recommendation.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sonerapp.recommendation.controller.RecommendationController;
import net.sonerapp.recommendation.dto.ModifyRecommendationDto;
import net.sonerapp.recommendation.dto.RecommendationDto;
import net.sonerapp.recommendation.service.RecommendationService;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Recommendation")
public class RecommendationControllerImpl implements RecommendationController {

        private final RecommendationService recommendationService;

        @Override
        @Operation(summary = "Get recommendations for a product", description = "Retrieve a list of recommendations for a specific product by its ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved recommendations.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecommendationDto[].class))),
                        @ApiResponse(responseCode = "403", description = "The product ID could not be converted to a UUID due to an argument type error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
        })
        public ResponseEntity<List<RecommendationDto>> getProductRecommendation(UUID productId, String correlationId) {
                log.debug("Correlation-id found: {}", correlationId);
                List<RecommendationDto> list = recommendationService.getProductRecommendation(productId);
                return ResponseEntity.ok(list);
        }

        @Override
        @Operation(summary = "Get recommendations page", description = "Retrieve a paginated list of recommendations.", responses = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated recommendations.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecommendationDto[].class)))
        })
        public ResponseEntity<List<RecommendationDto>> getRecommendationPage(Pageable pageable) {
                List<RecommendationDto> recommendationDtoPage = recommendationService.getRecommendationPage(pageable);
                return ResponseEntity.ok(recommendationDtoPage);
        }

        @Override
        @Operation(summary = "Create a new recommendation", description = "Create a new recommendation based on the provided details.", responses = {
                        @ApiResponse(responseCode = "201", description = "Successfully created a new recommendation.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecommendationDto.class))),
                        @ApiResponse(responseCode = "400", description = "Validation failed for the provided input.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
        })
        public ResponseEntity<RecommendationDto> createRecommendation(
                        @Valid @RequestBody ModifyRecommendationDto modifyRecommendationDto, UUID productId) {
                RecommendationDto recommendationDto = recommendationService
                                .createRecommendation(modifyRecommendationDto, productId);
                return ResponseEntity.status(HttpStatus.CREATED).body(recommendationDto);
        }

        @Override
        @Operation(summary = "Update an existing recommendation", description = "Update the details of an existing recommendation by its ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Successfully updated the recommendation.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecommendationDto.class))),
                        @ApiResponse(responseCode = "400", description = "Validation failed for the provided input.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
                        @ApiResponse(responseCode = "403", description = "The recommendation ID could not be converted to a UUID due to an argument type error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
                        @ApiResponse(responseCode = "404", description = "The recommendation with the specified ID was not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
        })
        public ResponseEntity<RecommendationDto> updateRecommendation(
                        @Valid ModifyRecommendationDto modifyRecommendationDto, UUID recommendationId) {
                RecommendationDto recommendationDto = recommendationService.updateRecommendation(
                                modifyRecommendationDto,
                                recommendationId);
                return ResponseEntity.ok(recommendationDto);
        }

        @Override
        @Operation(summary = "Delete a recommendation", description = "Delete an existing recommendation by its ID.", responses = {
                        @ApiResponse(responseCode = "204", description = "Successfully deleted the recommendation.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                        @ApiResponse(responseCode = "400", description = "Validation failed for the provided input.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
                        @ApiResponse(responseCode = "403", description = "The recommendation ID could not be converted to a UUID due to an argument type error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
                        @ApiResponse(responseCode = "404", description = "The recommendation with the specified ID was not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
        })
        public ResponseEntity<Void> deleteRecommendation(UUID recommendationId) {
                recommendationService.deleteRecommendation(recommendationId);
                return ResponseEntity.noContent().build();
        }

}
