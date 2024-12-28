package net.sonerapp.product_aggregator.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;

public interface ProductAggregatorController {
    @GetMapping(value = "/{productId}")
    ResponseEntity<ProductAggregatorDto> getProduct(@PathVariable UUID productId);
}
