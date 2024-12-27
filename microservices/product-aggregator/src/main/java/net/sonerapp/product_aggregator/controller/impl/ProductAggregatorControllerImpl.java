package net.sonerapp.product_aggregator.controller.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.sonerapp.product_aggregator.controller.ProductAggregatorController;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.service.ProductAggregatorService;

@RestController
@AllArgsConstructor
public class ProductAggregatorControllerImpl implements ProductAggregatorController {

    private final ProductAggregatorService productAggregatorService;

    @Override
    public ResponseEntity<ProductAggregatorDto> getProduct(UUID productId) {
        ProductAggregatorDto productAggregatorDto = productAggregatorService.aggregateProduct(productId);
        return ResponseEntity.ok(productAggregatorDto);
    }

}
