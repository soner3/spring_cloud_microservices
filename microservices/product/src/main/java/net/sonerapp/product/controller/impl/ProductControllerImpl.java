package net.sonerapp.product.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sonerapp.product.controller.ProductController;
import net.sonerapp.product.dto.ModifyProductDto;
import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ResponseEntity<ProductDto> getProduct(UUID productId) {
        ProductDto productDto = productService.getProduct(productId);
        return ResponseEntity.ok(productDto);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductPage(Pageable pageable) {
        List<ProductDto> productDtoPage = productService.getProductPage(pageable);
        return ResponseEntity.ok(productDtoPage);
    }

    @Override
    public ResponseEntity<ProductDto> createProduct(@Valid ModifyProductDto modifyProductDto) {
        ProductDto productDto = productService.createProduct(modifyProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(@Valid ModifyProductDto modifyProductDto, UUID productId) {
        ProductDto productDto = productService.updateProduct(modifyProductDto, productId);
        return ResponseEntity.ok().body(productDto);
    }

}
