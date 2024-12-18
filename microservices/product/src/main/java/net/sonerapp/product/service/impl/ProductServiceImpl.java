package net.sonerapp.product.service.impl;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.entity.Product;
import net.sonerapp.product.exception.InvalidInputException;
import net.sonerapp.product.exception.NotFoundException;
import net.sonerapp.product.repository.ProductRepository;
import net.sonerapp.product.service.ProductService;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ConversionService conversionService;

    @Override
    public ProductDto getProduct(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid product-id: " + productId);
        }
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("No product found for product-id: " + productId));
        return conversionService.convert(product, ProductDto.class);
    }

}
