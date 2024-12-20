package net.sonerapp.product.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.sonerapp.product.controller.ProductController;
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

}
