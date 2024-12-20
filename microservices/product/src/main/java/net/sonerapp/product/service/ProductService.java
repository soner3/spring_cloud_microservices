package net.sonerapp.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import net.sonerapp.product.dto.CreateProductDto;
import net.sonerapp.product.dto.ProductDto;

public interface ProductService {

    ProductDto getProduct(UUID productId);

    List<ProductDto> getProductPage(Pageable pageable);

    ProductDto createProduct(CreateProductDto createProductDto);
}
