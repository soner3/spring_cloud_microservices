package net.sonerapp.product_aggregator.controller.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sonerapp.product_aggregator.controller.ProductAggregatorController;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ModifyProductDto;
import net.sonerapp.product_aggregator.service.ProductAggregatorService;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Product Aggregator", description = "API to fetch aggregated product data, including recommendations and reviews.")
public class ProductAggregatorControllerImpl implements ProductAggregatorController {

    private final ProductAggregatorService productAggregatorService;

    @Override
    @Operation(summary = "Aggregates product data", description = "Fetches product details, recommendations, and reviews for the specified product.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful response containing aggregated product data.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductAggregatorDto.class))),
            @ApiResponse(responseCode = "403", description = "The provided product ID could not be converted into a valid UUID.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "No product was found with the given ID.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "An unhandled error occurred in one of the REST clients.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ProductAggregatorDto> getProduct(String correlationId, UUID productId) {
        ProductAggregatorDto productAggregatorDto = productAggregatorService.aggregateProduct(productId, correlationId);
        return ResponseEntity.ok(productAggregatorDto);
    }

    @Override
    public ResponseEntity<Void> createProduct(String correlationId, @Valid ModifyProductDto modifyProductDto) {
        productAggregatorService.createProduct(modifyProductDto, correlationId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String correlationId, UUID productId) {
        productAggregatorService.deleteProduct(correlationId, productId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> updateProduct(String correlationId, @Valid ModifyProductDto modifyProductDto,
            UUID productId) {
        productAggregatorService.updateProduct(modifyProductDto, correlationId, productId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
