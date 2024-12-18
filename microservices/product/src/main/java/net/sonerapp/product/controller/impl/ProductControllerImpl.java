package net.sonerapp.product.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.sonerapp.product.controller.ProductController;
import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.service.ProductService;

@RestController
@AllArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ResponseEntity<ProductDto> getProduct(int productId) {
        ProductDto productDto = productService.getProduct(productId);
        return ResponseEntity.ok(productDto);
    }

}
