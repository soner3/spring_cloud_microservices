package net.sonerapp.product_aggregator.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import net.sonerapp.product_aggregator.controller.ProductAggregatorController;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;

@RestController
public class ProductAggregatorControllerImpl implements ProductAggregatorController {

    @Override
    public ResponseEntity<ProductAggregatorDto> getProduct(int productId) {
        return ResponseEntity.ok(new ProductAggregatorDto(productId));
    }

}
