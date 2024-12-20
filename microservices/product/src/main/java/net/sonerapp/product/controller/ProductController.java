package net.sonerapp.product.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sonerapp.product.dto.ProductDto;

public interface ProductController {

    @GetMapping(value = "/product/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable UUID productId);

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductDto>> getProductPage(Pageable pageable);

}