package net.sonerapp.product_aggregator.service.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.product_aggregator.dto.ProductAggregatorDto;
import net.sonerapp.product_aggregator.dto.product.ProductDto;
import net.sonerapp.product_aggregator.service.ProductAggregatorService;
import net.sonerapp.product_aggregator.service.client.ProductFeignClient;

@AllArgsConstructor
@Service
public class ProductAggregatorServiceImpl implements ProductAggregatorService {

    private final ProductFeignClient productFeignClient;

    @Override
    public ProductAggregatorDto aggregateProduct(UUID productId) {
        ResponseEntity<ProductDto> productResponse = productFeignClient.getProduct(productId);
        return new ProductAggregatorDto(productResponse.getBody());
    }

}
