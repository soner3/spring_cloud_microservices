package net.sonerapp.product.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import net.sonerapp.product.dto.ModifyProductDto;
import net.sonerapp.product.dto.ProductDto;

public interface ProductController {

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable UUID productId);

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getProductPage(Pageable pageable);

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ModifyProductDto modifyProductDto);

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ModifyProductDto modifyProductDto,
            @PathVariable UUID productId);

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId);

}