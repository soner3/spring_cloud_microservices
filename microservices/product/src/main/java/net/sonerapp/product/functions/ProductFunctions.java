package net.sonerapp.product.functions;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.product.dto.ModifyProductDto;
import net.sonerapp.product.dto.ProductEventDto;
import net.sonerapp.product.exception.InvalidEventDataException;
import net.sonerapp.product.service.ProductService;

@Configuration
@Slf4j
public class ProductFunctions {

    @Bean
    Consumer<Event<ProductEventDto, String>> productHandler(ProductService productService) {
        return event -> {
            String correlationId = event.getKey();
            log.info("Received Message for correlation-id: {}", correlationId);
            ProductEventDto productEventDto = event.getData();
            switch (event.getEventType()) {
                case CREATE:
                    log.info("Create product for correlation-id: {}", correlationId);
                    ModifyProductDto createModifyProductDto = extractModifyProductDto(productEventDto, correlationId);
                    productService.createProduct(createModifyProductDto);
                    break;
                case UPDATE:
                    log.info("Update product for correlation-id: {}", correlationId);
                    ModifyProductDto updateModifyProductDto = extractModifyProductDto(productEventDto, correlationId);
                    UUID updateProductId = extractProductId(productEventDto, correlationId);
                    productService.updateProduct(updateModifyProductDto, updateProductId);
                    break;
                case DELETE:
                    log.info("Delete product for correlation-id: {}", correlationId);
                    UUID deleteProductId = extractProductId(productEventDto, correlationId);
                    productService.deleteProduct(deleteProductId);
                    break;
                default:
                    log.error("Could not handle product event for correlation-id: {}", correlationId);
                    throw new InvalidEventDataException(
                            "No valid event type has been sent for correlation-id: " + correlationId);
            }
        };

    }

    private ModifyProductDto extractModifyProductDto(ProductEventDto productEventDto, String correlationId) {
        return productEventDto
                .modifyProductDto()
                .orElseThrow(() -> new InvalidEventDataException(
                        "No Modify-Product-Dto found in event data for correlation-id: " + correlationId));
    }

    private UUID extractProductId(ProductEventDto productEventDto, String correlationId) {
        return productEventDto
                .productId()
                .orElseThrow(() -> new InvalidEventDataException(
                        "No Product-Id found in event data for correlation-id: " + correlationId));
    }

}
