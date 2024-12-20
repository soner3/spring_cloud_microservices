package net.sonerapp.product.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sonerapp.product.dto.ModifyProductDto;
import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.entity.Product;
import net.sonerapp.product.exception.NotFoundException;
import net.sonerapp.product.repository.ProductRepository;
import net.sonerapp.product.service.ProductService;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ConversionService conversionService;

    @Override
    public ProductDto getProduct(UUID productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("No product found for id: " + productId.toString()));
        return conversionService.convert(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductPage(Pageable pageable) {
        return productRepository.findAll(pageable).stream()
                .map(product -> conversionService.convert(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ModifyProductDto modifyProductDto) {
        Product product = new Product(modifyProductDto.name(), modifyProductDto.weight(), modifyProductDto.price());
        while (productRepository.existsByProductId(product.getProductId())) {
            product.setProductId(UUID.randomUUID());
        }
        Product savedProduct = productRepository.save(product);
        return conversionService.convert(savedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(UUID productId) {
        int deletedEntityCount = productRepository.deleteByProductId(productId);
        if (deletedEntityCount < 1) {
            throw new NotFoundException("No product found for id: " + productId.toString());
        }
    }

    @Override
    public ProductDto updateProduct(ModifyProductDto modifyProductDto, UUID productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("No product found for id: " + productId.toString()));
        product.setName(modifyProductDto.name());
        product.setWeight(modifyProductDto.weight());
        product.setPrice(modifyProductDto.price());

        Product updatedProduct = productRepository.save(product);

        return conversionService.convert(updatedProduct, ProductDto.class);
    }

}
