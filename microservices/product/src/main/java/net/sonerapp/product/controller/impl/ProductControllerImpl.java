package net.sonerapp.product.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import net.sonerapp.product.controller.ProductController;
import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.exception.InvalidInputException;
import net.sonerapp.product.exception.NotFoundException;

@RestController
public class ProductControllerImpl implements ProductController {

    @Override
    public ResponseEntity<ProductDto> getProduct(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid product-id: " + productId);
        }
        if (productId == 13) {
            throw new NotFoundException("No product found for product-id: " + productId);
        }
        ProductDto productDto = new ProductDto(productId, "Product-" + productId, 23);
        return ResponseEntity.ok(productDto);

    }

}
