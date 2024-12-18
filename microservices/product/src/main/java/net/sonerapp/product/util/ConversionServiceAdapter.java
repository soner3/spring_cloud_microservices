package net.sonerapp.product.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.entity.Product;

@Component
public class ConversionServiceAdapter {

    private final ConversionService conversionService;

    public ConversionServiceAdapter(@Lazy final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public ProductDto productToProductDto(final Product product) {
        return (ProductDto) conversionService.convert(product, TypeDescriptor.valueOf(Product.class),
                TypeDescriptor.valueOf(ProductDto.class));
    }

}
