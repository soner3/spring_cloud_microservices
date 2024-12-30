package net.sonerapp.product_aggregator.dto.product;

import java.util.Optional;
import java.util.UUID;

public record ProductEventDto(Optional<ModifyProductDto> modifyProductDto, Optional<UUID> productId) {

}
