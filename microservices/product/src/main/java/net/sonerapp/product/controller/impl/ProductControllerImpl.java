package net.sonerapp.product.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sonerapp.product.controller.ProductController;
import net.sonerapp.product.dto.ModifyProductDto;
import net.sonerapp.product.dto.ProductDto;
import net.sonerapp.product.service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Product API")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    @Operation(summary = "Get a product by ID", description = "Fetch a single product by its unique identifier.", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the product.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "403", description = "Invalid UUID format in the path variable.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Product with the specified ID not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ProductDto> getProduct(UUID productId) {
        ProductDto productDto = productService.getProduct(productId);
        return ResponseEntity.ok(productDto);
    }

    @Override
    @Operation(summary = "Get a paginated list of products", description = "Retrieve a list of products based on pagination parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of products.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto[].class))),
    })
    public ResponseEntity<List<ProductDto>> getProductPage(Pageable pageable) {
        List<ProductDto> productDtoPage = productService.getProductPage(pageable);
        return ResponseEntity.ok(productDtoPage);
    }

    @Override
    @Operation(summary = "Create a new product", description = "Add a new product to the catalog.", responses = {
            @ApiResponse(responseCode = "201", description = "Successfully created the product.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed for the input data.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ProductDto> createProduct(@Valid ModifyProductDto modifyProductDto) {
        ProductDto productDto = productService.createProduct(modifyProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @Override
    @Operation(summary = "Delete a product by ID", description = "Remove a product from the catalog using its unique identifier.", responses = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the product."),
            @ApiResponse(responseCode = "403", description = "Invalid UUID format in the path variable.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Product with the specified ID not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<Void> deleteProduct(UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Update an existing product", description = "Modify the details of an existing product using its unique identifier.", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the product.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed for the input data.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Invalid UUID format in the path variable.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Product with the specified ID not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ProductDto> updateProduct(@Valid ModifyProductDto modifyProductDto, UUID productId) {
        ProductDto productDto = productService.updateProduct(modifyProductDto, productId);
        return ResponseEntity.ok().body(productDto);
    }
}
