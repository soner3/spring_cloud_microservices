package net.sonerapp.product.dto;

import java.util.Optional;
import java.util.UUID;

public record ProductEventDto(Optional<ModifyProductDto> modifyProductDto, Optional<UUID> productId) {

}
