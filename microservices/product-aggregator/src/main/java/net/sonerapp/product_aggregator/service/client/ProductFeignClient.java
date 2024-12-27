package net.sonerapp.product_aggregator.service.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sonerapp.product_aggregator.dto.product.ProductDto;

@FeignClient("product")
public interface ProductFeignClient {

    @GetMapping(value = "/api/v1/product/{productId}", consumes = "application/json")
    ResponseEntity<ProductDto> getProduct(@PathVariable UUID productId);

}
