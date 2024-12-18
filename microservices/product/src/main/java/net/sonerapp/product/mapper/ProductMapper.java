package net.sonerapp.product.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Converter<Product, ProductDto> {

    ProductDto convert(Product product);

}
