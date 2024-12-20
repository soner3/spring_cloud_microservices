package net.sonerapp.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import net.sonerapp.product.dto.ModifyProductDto;
import net.sonerapp.product.dto.ProductDto;

public interface ProductService {

    ProductDto getProduct(UUID productId);

    List<ProductDto> getProductPage(Pageable pageable);

    ProductDto createProduct(ModifyProductDto modifyProductDto);

    ProductDto updateProduct(ModifyProductDto modifyProductDto, UUID productId);

    void deleteProduct(UUID productId);
}
