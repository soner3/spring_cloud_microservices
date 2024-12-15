package net.sonerapp.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.sonerapp.product.dto.ProductDto;

public interface ProductController {

    @GetMapping(value = "/product/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int productId);

}