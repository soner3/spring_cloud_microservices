package net.sonerapp.product_aggregator.service.client;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import net.sonerapp.product_aggregator.dto.product.ProductDto;

@HttpExchange
public interface ProductService {

    @GetExchange(value = "/v1/product/{productId}")
    ResponseEntity<ProductDto> getProduct(@PathVariable UUID productId);

}
